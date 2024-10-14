package com.example.northinkmobileandroid.data.model

data class LoginResponse(
    val userId: Int,
    val nome: String,
    val email: String,
    val token: String
)