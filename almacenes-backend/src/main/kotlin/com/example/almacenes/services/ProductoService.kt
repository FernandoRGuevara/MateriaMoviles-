package com.example.almacenes.services

import com.example.almacenes.model.Producto
import com.example.almacenes.repository.ProductoRepository
import org.springframework.stereotype.Service

@Service
class ProductoService(private val productoRepository: ProductoRepository) {

    fun obtenerProductoPorId(id: Long): Producto {
        return productoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Producto con ID $id no encontrado") }
    }

    fun listarProductos(): List<Producto> {
        return productoRepository.findAll()
    }

    fun getProductByUpc(upc: String): Producto? {
        return productoRepository.findByupc(upc)
    }
}