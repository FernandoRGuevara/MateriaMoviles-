package com.example.almacenes.data.model

data class Almacen(
    val id: Long = 0,
    val nombre: String,
    val direccion: String,
    val latitud: Double,
    val longitud: Double
)