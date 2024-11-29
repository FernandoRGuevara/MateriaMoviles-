package com.example.almacenes.model

data class Transaccion(
    val upc: String,
    val cantidad: Int,
    val tipo: String,
    val destino: String
)
