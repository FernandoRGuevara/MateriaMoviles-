package com.example.almacenes.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.almacenes.data.model.TransaccionRequest
import com.example.almacenes.data.repository.TransaccionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransaccionViewModel @Inject constructor(
    private val repository: TransaccionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TransaccionUiState>(TransaccionUiState.Idle)
    val uiState: StateFlow<TransaccionUiState> = _uiState

    fun registrarTransaccion(transaccion: TransaccionRequest) {
        viewModelScope.launch {
            _uiState.value = TransaccionUiState.Loading
            val result = repository.registrarTransaccion(transaccion)
            _uiState.value = result.fold(
                onSuccess = { TransaccionUiState.Success("Transacción registrada correctamente") },
                onFailure = { TransaccionUiState.Error("Error: ${it.message}") }
            )
        }
    }
}

