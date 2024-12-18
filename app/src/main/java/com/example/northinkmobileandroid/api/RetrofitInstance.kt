package com.example.northinkmobileandroid.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://18.212.150.219"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val tatuadorApi: TatuadorApi by lazy {
        retrofit.create(TatuadorApi::class.java)
    }
}