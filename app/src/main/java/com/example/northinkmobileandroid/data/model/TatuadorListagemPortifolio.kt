package com.example.northinkmobileandroid.data.model

data class TatuadorListagemPortifolio(
    val id: Long,
    val fotoPerfil: String?,
    val nome: String,
    val sobrenome: String,
    val valorMin: Double?,
    val anosExperiencia: String?,
    val resumo: String?,
    val telefone: String?,
    val instagram: String,
    val estilos: List<Estilo>,
    val estudio: Estudio?
)
{
}