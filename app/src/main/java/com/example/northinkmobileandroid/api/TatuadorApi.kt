package com.example.northinkmobileandroid.api

import com.example.northinkmobileandroid.data.model.LoginRequest
import com.example.northinkmobileandroid.data.model.LoginResponse
import com.example.northinkmobileandroid.data.model.TatuadorCriacao
import com.example.northinkmobileandroid.data.model.TatuadorListagem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TatuadorApi {

    @GET("api/usuarios/geral")
    suspend fun getTatuadores(): List<TatuadorListagem>

    @POST("api/usuarios")
    suspend fun createTatuador(@Body tatuador: TatuadorCriacao): Response<Void>

    @POST("api/usuarios/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

}