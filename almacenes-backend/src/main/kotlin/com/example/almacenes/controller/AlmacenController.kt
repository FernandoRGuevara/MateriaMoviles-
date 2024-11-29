package com.example.almacenes.controller

import com.example.almacenes.dto.ProductoExistenciaDTO
import com.example.almacenes.dto.TransaccionDTO
import com.example.almacenes.model.Almacen
import com.example.almacenes.services.AlmacenService
import com.example.almacenes.repository.AlmacenRepository
import com.example.almacenes.repository.TransaccionRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/almacen")
class AlmacenController(
    val almacenRepository: AlmacenRepository,
    private val almacenService: AlmacenService,
    private val transaccionRepository: TransaccionRepository
    ) {

    @GetMapping
    fun listar(): List<Almacen> = almacenRepository.findAll()

    @PostMapping
    fun agregar(@RequestBody almacen: Almacen): Almacen = almacenRepository.save(almacen)

    @GetMapping("/{id}/existencias")
    fun getExistencias(@PathVariable id: Long): List<ProductoExistenciaDTO> {
        return almacenService.getExistencias(id)
    }


    @GetMapping("/{id}/transacciones")
    fun getTransaccionesPorAlmacen(@PathVariable id: Long): List<TransaccionDTO> {
        val almacen = almacenRepository.findById(id)
            .orElseThrow { NoSuchElementException("Almacén con ID $id no encontrado") }

        // Obtener todas las transacciones asociadas al almacén
        val transacciones = transaccionRepository.findByAlmacen(almacen)

        return transacciones.map { transaccion ->
            TransaccionDTO(
                productoNombre = transaccion.producto.descripcion,
                tipo = transaccion.tipo,
                cantidad = transaccion.cantidad,
                fecha = transaccion.fechaHora
            )
        }
    }
}
