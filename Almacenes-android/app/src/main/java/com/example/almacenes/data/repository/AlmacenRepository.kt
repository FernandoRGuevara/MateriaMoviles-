package com.example.almacenes.data.repository

import com.example.almacenes.data.model.Almacen

interface AlmacenRepository {
    suspend fun getAlmacenes(): List<Almacen>


}
