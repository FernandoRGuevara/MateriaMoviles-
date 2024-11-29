package com.example.almacenes.ui.screens.productos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.almacenes.data.model.ExistenciaProducto
import com.example.almacenes.data.model.Producto
import com.example.almacenes.data.repository.ExistenciaRepository
import com.example.almacenes.data.repository.ProductoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductosViewModel @Inject constructor(
    private val productoRepository: ProductoRepository,
    private val existenciaRepository: ExistenciaRepository
) : ViewModel() {

    private val _productos = MutableLiveData<List<Producto>>()
    val productos: LiveData<List<Producto>> get() = _productos

    private val _existencias = MutableLiveData<List<ExistenciaProducto>>()
    val existencias: LiveData<List<ExistenciaProducto>> get() = _existencias

    init {
        obtenerProductos()
    }

    fun obtenerProductos() {
        viewModelScope.launch {
            _productos.value = productoRepository.obtenerProductos()
            println(_productos.value)
        }
    }

    fun obtenerExistencias(id: Int) {
        viewModelScope.launch {
            _existencias.value = existenciaRepository.getExistenciasProducto(id)
            println(_productos.value)
        }
    }

    suspend fun obtenerCantidadSuspend(id: Int): List<ExistenciaProducto> {
        return withContext(Dispatchers.IO) {
            existenciaRepository.getExistenciasProducto(id)
        }
    }

}
