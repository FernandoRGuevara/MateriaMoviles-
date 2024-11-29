package com.example.almacenes.data.repository

import com.example.almacenes.data.model.Existencia
import com.example.almacenes.data.model.ExistenciaProducto
import com.example.almacenes.data.network.ApiService
import javax.inject.Inject

class ExistenciaRepositoryImpl @Inject constructor(
    private val apiService: ApiService // Suponiendo que tienes un ApiService para interactuar con la API
) : ExistenciaRepository {

    override suspend fun getExistenciasByAlmacen(almacenId: Long): List<Existencia> {
        return apiService.getExistenciasByAlmacen(almacenId) // Llamada a la API
    }

    override suspend fun getExistenciasProducto(productoId: Int): List<ExistenciaProducto> {
        return apiService.getExistenciasProducto(productoId) // Llamada a la API
    }
}
