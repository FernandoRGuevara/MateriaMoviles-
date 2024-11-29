package com.example.almacenes.controller

import com.example.almacenes.dto.ExistenciaAlmacenDTO
import com.example.almacenes.model.Producto
import com.example.almacenes.repository.ProductoRepository
import com.example.almacenes.repository.TransaccionRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/producto")
class ProductoController(
    val productoRepository: ProductoRepository,
    private val transaccionRepository: TransaccionRepository,
) {

    @GetMapping
    fun listar(): List<Producto> = productoRepository.findAll()

    @PostMapping
    fun agregar(@RequestBody producto: Producto): Producto = productoRepository.save(producto)

    @GetMapping("/{id}/existencias")
    fun getExistenciasPorProducto(@PathVariable id: Long): List<ExistenciaAlmacenDTO> {
        val producto = productoRepository.findById(id)
            .orElseThrow { NoSuchElementException("Producto con ID $id no encontrado") }

        // Obtener todas las transacciones asociadas al producto
        val transacciones = transaccionRepository.findByProducto(producto)

        // Calcular existencias por almacén
        val existencias = transacciones
            .groupBy { it.almacen }
            .map { (almacen, transacciones) ->
                val totalCantidad = transacciones.sumOf {
                    when (it.tipo.split(" ")[0].lowercase()) { // Toma solo la primera palabra
                        "entrada" -> it.cantidad
                        "salida" -> -it.cantidad
                        else -> 0 // Manejo de tipos desconocidos
                    }
                }
                ExistenciaAlmacenDTO(almacen.nombre, totalCantidad)
            }

        return existencias.filter { it.cantidad > 0 } // Filtrar almacenes con existencias > 0
    }
}