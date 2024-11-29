package com.example.almacenes.data.model

data class TransaccionRequest(
    val upc: String,
    val cantidad: Int,
    val tipoTransaccion: String,
    val destinoOrigen: String,
    val empleado: String,
    val almacen: String
)

