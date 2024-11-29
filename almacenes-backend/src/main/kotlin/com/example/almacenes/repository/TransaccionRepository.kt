package com.example.almacenes.repository

import com.example.almacenes.model.Almacen
import com.example.almacenes.model.Producto
import com.example.almacenes.model.Transaccion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TransaccionRepository : JpaRepository<Transaccion, Long> {

    @Query("SELECT SUM(t.cantidad) FROM Transaccion t WHERE t.producto.id = :productoId AND t.tipo = 'Entrada'")
    fun totalEntradas(@Param("productoId") productoId: Long): Int?

    @Query("SELECT SUM(t.cantidad) FROM Transaccion t WHERE t.producto.id = :productoId AND t.tipo = 'Salida'")
    fun totalSalidas(@Param("productoId") productoId: Long): Int?

    fun findByAlmacen(almacen: Almacen): List<Transaccion>

    fun findByProducto(producto: Producto): List<Transaccion>


}

