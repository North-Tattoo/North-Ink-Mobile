package com.example.northinkmobileandroid.data.model

data class TatuadorCriacao(
    val nome: String,
    val sobrenome: String,
    val cpf: String,
    val celular: String,
    val email: String,
    val senha: String,
    val resumo: String,
    val estilos: List<Estilo>
){
}