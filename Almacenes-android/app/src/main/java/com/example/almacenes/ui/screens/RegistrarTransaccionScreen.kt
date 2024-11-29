package com.example.almacenes.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.almacenes.data.model.TransaccionRequest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RegistrarTransaccionScreen(
    viewModel: TransaccionViewModel = hiltViewModel()
) {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val uiState by viewModel.uiState.collectAsState()

    val datos: MutableList<String> = mutableListOf()

    var upc by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var origenDestino by remember { mutableStateOf("") }
    var empleado by remember { mutableStateOf("") }
    var almacen by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            val qrData = result.contents
            if (!qrData.isNullOrEmpty()) {
                datos += qrData.split("|")
                upc = datos[0]
                cantidad = datos[1]
                tipo = datos[2]
                origenDestino = datos[3]
            }
        }
    )
    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(title = { Text("Registrar Transacción") })

        Button(
            onClick = {
                if (permissionState.status.isGranted) {
                    scanLauncher.launch(ScanOptions())
                } else {
                    permissionState.launchPermissionRequest()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Escanear Código QR")
        }

        InputWithLabel(
            label = "UPC",
            value = upc,
            onValueChange = { upc = it }
        )

        InputWithLabel(
            label = "Cantidad",
            value = cantidad,
            onValueChange = { cantidad = it },
            onlyNumber = true
        )

        InputWithLabel(
            label = "Tipo",
            value = tipo,
            onValueChange = { tipo = it }
        )

        InputWithLabel(
            label = "Destino de origen",
            value = origenDestino,
            onValueChange = { origenDestino = it }
        )

        InputWithLabel(
            label = "Empleado",
            value = empleado,
            onValueChange = { empleado = it }
        )

        InputWithLabel(
            label = "Almacén",
            value = almacen,
            onValueChange = { almacen = it }
        )


        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Button(
            onClick = {
                // Validar campos
                when {
                    upc.isBlank() -> errorMessage = "El campo 'UPC' no puede estar vacío."
                    cantidad.isBlank() -> errorMessage = "El campo 'Cantidad' no puede estar vacío."
                    !cantidad.matches(Regex("\\d+")) -> errorMessage =
                        "El campo 'Cantidad' debe ser un número válido."

                    tipo.isBlank() -> errorMessage = "El campo 'Tipo' no puede estar vacío."
                    origenDestino.isBlank() -> errorMessage =
                        "El campo 'Destino de origen' no puede estar vacío."

                    empleado.isBlank() -> errorMessage = "El campo 'Empleado' no puede estar vacío."
                    almacen.isBlank() -> errorMessage = "El campo 'Almacén' no puede estar vacío."
                    else -> {
                        // Campos válidos, procesar la transacción
                        val transaccion = TransaccionRequest(
                            upc = upc,
                            cantidad = cantidad.toInt(),
                            tipoTransaccion = tipo,
                            destinoOrigen = origenDestino,
                            empleado = empleado,
                            almacen = almacen
                        )
                        println(transaccion)
                        viewModel.registrarTransaccion(transaccion)
                        errorMessage = null // Limpiar el mensaje de error después del éxito
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Registrar transacción")
        }

        RegistrarTransaccionScreenFeedback(uiState = uiState)

    }
}


@Composable
fun InputWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    onlyNumber: Boolean = false
) {
    val keyboardOptions = if (onlyNumber)
        KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ) else KeyboardOptions.Default
    OutlinedTextField(
        value = value,
        onValueChange = if (onlyNumber) { newValue ->
            if (newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        } else onValueChange,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions
    )
}
