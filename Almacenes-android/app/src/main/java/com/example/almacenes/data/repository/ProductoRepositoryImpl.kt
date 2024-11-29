package com.example.almacenes.data.repository

import com.example.almacenes.data.model.Producto
import com.example.almacenes.data.network.ApiService
import javax.inject.Inject

class ProductoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductoRepository {
    override suspend fun obtenerProductos(): List<Producto> {
        return try {
            apiService.obtenerProductos()
        } catch (e: Exception) {
            listOf()
        }
    }
}

