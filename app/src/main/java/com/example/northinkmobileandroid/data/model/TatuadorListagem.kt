package com.example.northinkmobileandroid.data.model

data class TatuadorListagem(
    val id: Int?,
    val nome: String,
    val sobrenome: String,
    val cpf: String,
    val celular: String,
    val email: String,
    val senha: String,
    val resumo: String,
    val anosExperiencia: String,
    val precoMinimo: Double,
    val instagram: String,
    val estilos: List<Estilo>,
    val tatuagens: List<Tatuagem>
)
{

}