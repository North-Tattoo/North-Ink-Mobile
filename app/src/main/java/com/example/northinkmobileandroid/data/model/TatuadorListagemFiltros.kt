package com.example.northinkmobileandroid.data.model

data class TatuadorListagemFiltros (
    val nome: String,
    val cidade: String,
    val precoMinimo: Double,
    val estilos: List<Estilo>
)
{

}