package com.example.northinkmobileandroid.data.model

data class LoginResponse(
    val userId: Long,
    val nome: String,
    val email: String,
    val token: String
)