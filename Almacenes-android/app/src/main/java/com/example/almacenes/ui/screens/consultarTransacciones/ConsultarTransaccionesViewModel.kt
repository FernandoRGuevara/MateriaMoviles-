package com.example.almacenes.ui.screens.consultarTransacciones

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.almacenes.data.model.TransaccionDTO
import com.example.almacenes.data.repository.TransaccionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsultarTransaccionesViewModel @Inject constructor(
    private val repository: TransaccionRepository
) : ViewModel() {

    private val _transacciones = MutableLiveData<List<TransaccionDTO>>()
    val transacciones: LiveData<List<TransaccionDTO>> get() = _transacciones

    fun consultarTransacciones(id: Int) {
        viewModelScope.launch {
            _transacciones.value = repository.consultarTransacciones(id)

        }
    }
}

