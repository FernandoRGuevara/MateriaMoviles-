package com.example.almacenes.data.repository

import com.example.almacenes.data.model.TransaccionDTO
import com.example.almacenes.data.model.TransaccionRequest
import com.example.almacenes.data.network.ApiService
import javax.inject.Inject

class TransaccionRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TransaccionRepository {
    override suspend fun registrarTransaccion(transaccionRequest: TransaccionRequest): Result<String> {
        return try {
            val response = apiService.registrarTransaccion(transaccionRequest)
            if (response.isSuccessful) {
                val responseBody = response.body()
                val message = responseBody?.get("message") ?: "Transacción registrada."
                Result.success(message)
            } else {
                Result.failure(Exception("Datos incorrectos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun consultarTransacciones(id: Int): List<TransaccionDTO> {
        return apiService.getTransaccionesByAlmacen(id) // Llamada a la API
    }
}

