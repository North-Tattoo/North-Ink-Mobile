package com.example.northinkmobileandroid

data class Tatuador(
    val id: Int?,
    val nome: String,
    val sobrenome: String,
    val cpf: String,
    val celular: String,
    val email: String,
    val resumo: String,
    val anosExperiencia: Int?,
    val precoMinimo: Double?,
    val instagram: String?,
    val estilos: List<Estilo>,
    val tatuagens: List<Tatuagem>
){
}