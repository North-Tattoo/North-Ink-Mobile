package com.example.northinkmobileandroid.data.model

data class TatuadorListagem(
    val id: Int?,
    val nome: String,
    val sobrenome: String?,
    val precoMin: Double?,
    val instagram: String?,
    val estilos: List<Estilo>,
    val estudio: Estudio?
)
{

}