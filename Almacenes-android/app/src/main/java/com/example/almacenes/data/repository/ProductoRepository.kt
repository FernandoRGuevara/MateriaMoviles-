package com.example.almacenes.data.repository

import com.example.almacenes.data.model.Producto

interface ProductoRepository {
    suspend fun obtenerProductos(): List<Producto>
}
