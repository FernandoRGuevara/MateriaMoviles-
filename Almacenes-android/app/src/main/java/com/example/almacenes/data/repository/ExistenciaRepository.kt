package com.example.almacenes.data.repository

import com.example.almacenes.data.model.Existencia
import com.example.almacenes.data.model.ExistenciaProducto

interface ExistenciaRepository {
    suspend fun getExistenciasByAlmacen(almacenId: Long): List<Existencia>

    suspend fun getExistenciasProducto(productoId: Int): List<ExistenciaProducto>

}
