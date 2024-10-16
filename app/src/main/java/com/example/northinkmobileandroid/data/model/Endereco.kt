package com.example.northinkmobileandroid.data.model

data class Endereco(
    val id: Int?,
    val rua: String,
    val numero: Int,
    val complemento: String,
    val cep: String,
    val bairro: String,
    val cidade: String,
    val estado: String
) {
}