package com.example.northinkmobileandroid.api.service

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.File

suspend fun uploadImageToCloudinary(context: Context, imageFile: File): String? {
    val apiKey = "883746148284493"
    val uploadPreset = "upload-tatto"
    val cloudName = "dvbc2jwny"

    // Abre o stream do arquivo
    val requestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())

    // Cria o corpo multipart com o arquivo
    val formBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("upload_preset", uploadPreset)
        .addFormDataPart("file", imageFile.name, requestBody)
        .build()

    // Configura o cliente HTTP
    val client = OkHttpClient()

    // Cria a requisição HTTP
    val request = Request.Builder()
        .url("https://api.cloudinary.com/v1_1/$cloudName/image/upload")
        .post(formBody)
        .build()

    return try {
        // Faz a requisição e obtém a resposta
        val response = client.newCall(request).execute()
        val jsonResponse = JSONObject(response.body?.string() ?: "")
        jsonResponse.optString("secure_url", null)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

