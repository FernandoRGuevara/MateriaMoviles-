package com.example.almacenes.services

import com.example.almacenes.model.Transaccion
import com.example.almacenes.repository.TransaccionRepository
import org.springframework.stereotype.Service

@Service
class TransaccionService(private val transaccionRepository: TransaccionRepository) {

    fun guardarTransaccion(transaccion: Transaccion): Transaccion {
        return transaccionRepository.save(transaccion)
    }

    fun listarTransacciones(): List<Transaccion> {
        return transaccionRepository.findAll()
    }
}
