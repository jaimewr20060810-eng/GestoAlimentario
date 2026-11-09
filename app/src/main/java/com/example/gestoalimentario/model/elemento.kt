package com.example.gestoalimentario.model

object  Estado{
    const val CADUCADO = "Caducado"
    const val NO_CADUCADO= "No caducado"
}

data class Receta (

    val id: Long,
    val nombre: String,
    val categoria: String,
    val fecha_compra: String,
    val fecha_caducado: String,
    val estado: String

)