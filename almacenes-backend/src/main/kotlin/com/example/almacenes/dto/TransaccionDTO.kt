package com.example.almacenes.dto

import java.time.LocalDateTime

data class TransaccionDTO(
    val productoNombre: String,
    val tipo: String,
    val cantidad: Int,
    val fecha: LocalDateTime
)
