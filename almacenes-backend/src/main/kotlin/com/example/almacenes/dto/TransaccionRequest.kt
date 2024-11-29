package com.example.almacenes.dto

data class TransaccionRequest(
    val almacenId: Long,
    val productoId: Long,
    val cantidad: Int,
    val tipo: String,
    val origenDestino: String,
    val empleado: String
)
