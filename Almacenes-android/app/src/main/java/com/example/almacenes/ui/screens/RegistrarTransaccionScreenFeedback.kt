package com.example.almacenes.ui.screens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RegistrarTransaccionScreenFeedback(
    uiState: TransaccionUiState
) {
    when (uiState) {
        is TransaccionUiState.Idle -> { /* No mostrar nada */ }
        is TransaccionUiState.Loading -> {
            CircularProgressIndicator()
        }
        is TransaccionUiState.Success -> {
            Text(modifier = Modifier.height(300.dp),text = uiState.mensaje, color = Color.Green)
        }
        is TransaccionUiState.Error -> {
            Text(modifier = Modifier.height(300.dp),text = uiState.mensaje, color = Color.Red)
        }
    }
}
