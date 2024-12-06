package com.example.northinkmobileandroid.api.service

import android.content.Context
import android.net.Uri
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.File
import com.example.northinkmobileandroid.di.SessaoUsuario
import okhttp3.Credentials
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class UploadService : KoinComponent {

    suspend fun uploadImagesToCloudinary(context: Context, imageFiles: List<File>): List<String?> {
        val apiKey = "883746148284493"
        val uploadPreset = "upload-tatto"
        val cloudName = "dvbc2jwny"

        // Recupera o SessaoUsuario via Koin
        val sessaoUsuario: SessaoUsuario by inject()

        // Recupera o userId e o nome do usuário da sessão
        val userId = sessaoUsuario.userId.toString()
        val userName = sessaoUsuario.nome.trim()

        val folderPath = "tatuadores/$userId/$userName/tattoos"

        val client = OkHttpClient()
        val responses = mutableListOf<String?>()

        Log.d("UploadService", "Arquivos selecionados: ${imageFiles.map { it.name }}")

        imageFiles.forEach { imageFile ->
            val requestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())

            val formBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("upload_preset", uploadPreset)
                .addFormDataPart("file", imageFile.name, requestBody)
                .addFormDataPart("folder", folderPath)
                .build()
            Log.d("UploadService", "Enviando arquivo: ${imageFile.name}")

            val request = Request.Builder()
                .url("https://api.cloudinary.com/v1_1/$cloudName/image/upload")
                .post(formBody)
                .build()

            try {
                val response = client.newCall(request).execute()
                val jsonResponse = JSONObject(response.body?.string() ?: "")
                responses.add(jsonResponse.optString("secure_url", null))
            } catch (e: Exception) {
                e.printStackTrace()
                responses.add(null)
            }
        }

        return responses
    }

//    FUNÇÃO QUE BUSCA AS IMAGENS DO CLOUDINARY

    suspend fun buscarImagensDaPastaCloudinary(folderPath: String): List<String> {
        val apiKey = "883746148284493"
        val apiSecret = "hSi_o9GWcuM5fJi3fvm7VHHMryo"
        val cloudName = "dvbc2jwny"

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url("https://api.cloudinary.com/v1_1/$cloudName/resources/image?prefix=$folderPath&type=upload")
            .addHeader("Authorization", Credentials.basic(apiKey, apiSecret))
            .build()

        try {
            val response = client.newCall(request).execute()

            // Garantir que a resposta seja lida uma única vez
            val responseBody = response.body?.string()
            if (!response.isSuccessful) {
                Log.e("CloudinaryAPI", "Erro ao acessar Cloudinary: ${response.code}")
                throw Exception("Erro ao acessar Cloudinary: ${response.code}")
            }

            val jsonResponse = JSONObject(responseBody.orEmpty())
            val resources = jsonResponse.optJSONArray("resources") ?: return emptyList()

            return List(resources.length()) { index ->
                val resource = resources.getJSONObject(index)
                resource.optString("secure_url")
            }
        } catch (e: Exception) {
            Log.e("CloudinaryAPI", "Erro ao buscar imagens: ${e.message}", e)
            throw e
        }
    }

    suspend fun uploadProfilePicture(context: Context, profileImageFile: File): String? {
        val apiKey = "883746148284493"
        val uploadPreset = "upload-tatto"
        val cloudName = "dvbc2jwny"

        val sessaoUsuario: SessaoUsuario by inject()

        val userId = sessaoUsuario.userId.toString()
        val userName = sessaoUsuario.nome.trim()

            val folderPath = "tatuadores/$userId/$userName/profile_picture/"

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        Log.d("UploadService", "Enviando foto de perfil: ${profileImageFile.name}")

        val requestBody = profileImageFile.asRequestBody("image/*".toMediaTypeOrNull())

        val formBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("upload_preset", uploadPreset)
            .addFormDataPart("file", profileImageFile.name, requestBody)
            .addFormDataPart("folder", folderPath)
            .build()

        val request = Request.Builder()
            .url("https://api.cloudinary.com/v1_1/$cloudName/image/upload")
            .post(formBody)
            .build()

        return try {
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()

            if (!response.isSuccessful) {
                Log.e("UploadService", "Erro no upload da foto de perfil: ${response.code}")
                throw Exception("Erro ao fazer upload da foto de perfil: ${response.code}")
            }

            val jsonResponse = JSONObject(responseBody.orEmpty())
            jsonResponse.optString("secure_url", null).also {
                Log.d("UploadService", "Foto de perfil enviada com sucesso: $it")
            }
        } catch (e: Exception) {
            Log.e("UploadService", "Erro ao enviar a foto de perfil: ${e.message}", e)
            null
        }
    }


}
