package com.example.almacenes.dto

data class TransaccionQRRequest(
    val upc: String,
    val cantidad: Int,
    val tipoTransaccion: String,
    val destinoOrigen: String,
    val empleado: String,
    val almacen: String
)