package com.example.almacenes.data.repository

import com.example.almacenes.data.model.TransaccionDTO
import com.example.almacenes.data.model.TransaccionRequest

interface TransaccionRepository {
    suspend fun registrarTransaccion(transaccion: TransaccionRequest): Result<String>

    suspend fun consultarTransacciones(id: Int): List<TransaccionDTO>

}
