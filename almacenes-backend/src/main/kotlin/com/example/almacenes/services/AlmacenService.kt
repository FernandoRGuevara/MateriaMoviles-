package com.example.almacenes.services

import com.example.almacenes.dto.ProductoExistenciaDTO
import com.example.almacenes.model.Almacen
import com.example.almacenes.repository.AlmacenRepository
import com.example.almacenes.repository.ProductoRepository
import com.example.almacenes.repository.TransaccionRepository
import org.springframework.stereotype.Service

@Service
class AlmacenService(
    private val almacenRepository: AlmacenRepository,
    private val productoRepository: ProductoRepository,
    private val transaccionRepository: TransaccionRepository
) {

    fun obtenerAlmacenPorDestinoOrigen(nombre: String): Almacen? {
        return almacenRepository.findBynombre(nombre)
    }

    fun obtenerAlmacenPorId(id: Long): Almacen {
        return almacenRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Almacén con ID $id no encontrado") }
    }

    fun listarAlmacenes(): List<Almacen> {
        return almacenRepository.findAll()
    }

    fun getExistencias(almacenId: Long): List<ProductoExistenciaDTO> {
        val almacen = almacenRepository.findById(almacenId)
            .orElseThrow { NoSuchElementException("Almacén con ID $almacenId no encontrado") }

        // Obtener todas las transacciones del almacén
        val transacciones = transaccionRepository.findByAlmacen(almacen)

        // Calcular existencias agrupando por producto
        val existencias = transacciones
            .groupBy { it.producto }
            .map { (producto, transacciones) ->
                val totalCantidad = transacciones.sumOf { if (it.tipo == "Entrada") it.cantidad else -it.cantidad }
                ProductoExistenciaDTO(producto.descripcion, totalCantidad) // Aquí usamos producto.descripcion
            }

        return existencias.filter { it.cantidad > 0 } // Filtrar productos con existencias > 0
    }

}