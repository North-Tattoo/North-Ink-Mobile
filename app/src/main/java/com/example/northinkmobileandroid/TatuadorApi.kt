package com.example.northinkmobileandroid

import retrofit2.http.GET

interface TatuadorApi {

    @GET("api/usuarios")
    suspend fun getTatuadores(): List<Tatuador>

}