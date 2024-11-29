package com.example.almacenes.data.repository

import com.example.almacenes.data.model.Almacen
import com.example.almacenes.data.network.ApiService
import javax.inject.Inject

class AlmacenRepositoryImpl @Inject constructor(
    private val apiService: ApiService // Suponiendo que tienes un ApiService para interactuar con la API
) : AlmacenRepository {

    override suspend fun getAlmacenes(): List<Almacen> {
        return apiService.getAlmacenes() // Llamada a la API
    }

}
