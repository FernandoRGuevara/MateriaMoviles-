package com.example.almacenes.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Transaccion(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    val almacen: Almacen,

    @ManyToOne
    val producto: Producto,

    val cantidad: Int,
    val tipo: String, // Entrada o Salida
    val fechaHora: LocalDateTime = LocalDateTime.now(),

    val origenDestino: String, // Cliente, Proveedor o Almacén
    val empleado: String
)
