package com.example.almacenes.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.almacenes.data.model.Existencia
import com.example.almacenes.data.repository.ExistenciaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExistenciaViewModel @Inject constructor(
    private val existenciaRepository: ExistenciaRepository
) : ViewModel() {

    private val _existencias = MutableLiveData<List<Existencia>>()
    val existencias: LiveData<List<Existencia>> get() = _existencias

    fun getExistenciasByAlmacen(almacenId: Long) {
        viewModelScope.launch {
            _existencias.value = existenciaRepository.getExistenciasByAlmacen(almacenId)
        }
    }
}
