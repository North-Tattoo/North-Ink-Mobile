package com.example.northinkmobileandroid.di


data class SessaoUsuario(
    var userId: Long,
    var email: String = "",
    var nome: String = "",
    var token: String = ""
)