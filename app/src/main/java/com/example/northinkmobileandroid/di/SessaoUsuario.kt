package com.example.northinkmobileandroid.di

import java.time.LocalDateTime

data class SessaoUsuario(
    var userId: Long,
    var login: String = "",
    var nome: String = "",
    var token: String = ""
)