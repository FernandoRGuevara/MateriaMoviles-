package com.example.almacenes.repository

import com.example.almacenes.model.Producto
import org.springframework.data.jpa.repository.JpaRepository

interface ProductoRepository : JpaRepository<Producto, Long> {
    fun findByupc(upc: String): Producto?
}