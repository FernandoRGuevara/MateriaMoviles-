package com.example.almacenes.ui.screens.almacenes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.almacenes.data.model.Almacen
import com.example.almacenes.data.repository.AlmacenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlmacenesViewModel @Inject constructor(
    private val almacenRepository: AlmacenRepository
) : ViewModel() {

    private val _almacenes = MutableLiveData<List<Almacen>>()
    val almacenes: LiveData<List<Almacen>> get() = _almacenes

    init {
        obtenerAlmacenes()
    }

    fun obtenerAlmacenes() {
        viewModelScope.launch {
            _almacenes.value = almacenRepository.getAlmacenes()
            println(_almacenes.value)
        }
    }

}
