package com.example.almacenes.data.network

import com.example.almacenes.data.model.Almacen
import com.example.almacenes.data.model.Existencia
import com.example.almacenes.data.model.ExistenciaProducto
import com.example.almacenes.data.model.Producto
import com.example.almacenes.data.model.TransaccionDTO
import com.example.almacenes.data.model.TransaccionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("almacen/{almacenId}/existencias")
    suspend fun getExistenciasByAlmacen(@Path("almacenId") almacenId: Long): List<Existencia>

    @GET("almacen/{almacenId}/transacciones")
    suspend fun getTransaccionesByAlmacen(@Path("almacenId") almacenId: Int): List<TransaccionDTO>

    @GET("almacen")
    suspend fun getAlmacenes(): List<Almacen>

    @POST("transaccionQR")
    suspend fun registrarTransaccion(@Body transaccionRequest: TransaccionRequest): Response<Map<String, String>>

    @GET("producto")
    suspend fun obtenerProductos(): List<Producto>

    @GET("producto/{productoId}/existencias")
    suspend fun getExistenciasProducto(@Path("productoId") productoId: Int): List<ExistenciaProducto>
}
