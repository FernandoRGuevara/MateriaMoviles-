package com.example.almacenes.ui.screens

sealed class TransaccionUiState {
    object Idle : TransaccionUiState()
    object Loading : TransaccionUiState()
    data class Success(val mensaje: String) : TransaccionUiState()
    data class Error(val mensaje: String) : TransaccionUiState()
}
