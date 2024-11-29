package com.example.almacenes.controller

import com.example.almacenes.dto.TransaccionQRRequest
import com.example.almacenes.dto.TransaccionRequest
import com.example.almacenes.model.Transaccion
import com.example.almacenes.services.AlmacenService
import com.example.almacenes.services.ProductoService
import com.example.almacenes.services.TransaccionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class TransaccionController(
    private val almacenService: AlmacenService,
    private val productoService: ProductoService,
    private val transaccionService: TransaccionService
) {
    @PostMapping("/transaccion")
    fun registrarTransaccion(@RequestBody transaccionRequest: TransaccionRequest): ResponseEntity<Transaccion> {
        // Obtener el almacen y producto usando los servicios
        val almacen = almacenService.obtenerAlmacenPorId(transaccionRequest.almacenId)
        val producto = productoService.obtenerProductoPorId(transaccionRequest.productoId)

        // Crear la entidad Transaccion
        val transaccion = Transaccion(
            almacen = almacen,
            producto = producto,
            cantidad = transaccionRequest.cantidad,
            tipo = transaccionRequest.tipo,
            origenDestino = transaccionRequest.origenDestino,
            empleado = transaccionRequest.empleado,
            fechaHora = LocalDateTime.now()
        )

        // Guardar la transacción
        val transaccionGuardada = transaccionService.guardarTransaccion(transaccion)

        return ResponseEntity.status(HttpStatus.CREATED).body(transaccionGuardada)
    }

    @PostMapping("/transaccionQR")
    fun registrarTransaccionQR(@RequestBody transaccionRequest: TransaccionQRRequest): ResponseEntity<Map<String, String>> {
        // Obtener el almacen y producto usando los servicios
        val almacen = almacenService.obtenerAlmacenPorDestinoOrigen(transaccionRequest.almacen)
        val producto = productoService.getProductByUpc(transaccionRequest.upc)

        // Crear la entidad Transaccion
        var transaccion = almacen?.let { almacenValue ->
            producto?.let { productoValue ->
                Transaccion(
                    almacen = almacenValue,
                    producto = productoValue,
                    cantidad = transaccionRequest.cantidad,
                    tipo = transaccionRequest.tipoTransaccion,
                    origenDestino = transaccionRequest.destinoOrigen,
                    empleado = transaccionRequest.empleado,
                    fechaHora = LocalDateTime.now()
                )
            }
        }

        return if (transaccion != null) {
            // Guardar la transacción
            val transaccionGuardada = transaccionService.guardarTransaccion(transaccion)
            return ResponseEntity.ok(mapOf("mensaje" to "Transacción creada"))
            //return ResponseEntity.ok(transaccionGuardada)
            //return ResponseEntity.status(HttpStatus.CREATED).body(transaccionGuardada)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}