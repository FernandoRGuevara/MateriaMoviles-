package com.example.almacenes.repository

import com.example.almacenes.model.Almacen
import org.springframework.data.jpa.repository.JpaRepository

interface AlmacenRepository : JpaRepository<Almacen, Long> {
    fun findBynombre(nombre: String): Almacen?
}