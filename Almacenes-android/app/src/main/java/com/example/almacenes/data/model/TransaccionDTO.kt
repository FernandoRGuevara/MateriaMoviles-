package com.example.almacenes.data.model


data class TransaccionDTO(
    val productoNombre: String,
    val tipo: String,
    val cantidad: Int,
    val fecha: String
)
