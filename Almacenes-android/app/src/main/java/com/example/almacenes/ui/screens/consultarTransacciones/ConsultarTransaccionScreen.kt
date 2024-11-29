package com.example.almacenes.ui.screens.consultarTransacciones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.almacenes.ui.screens.InputWithLabel
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ConsultarTransaccionScreen(
    navController: NavController,
    viewModel: ConsultarTransaccionesViewModel = hiltViewModel(),
) {

    val transacciones by viewModel.transacciones.observeAsState(emptyList())

    var id by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(title = { Text("Consultar Transacción") })

        InputWithLabel(
            label = "ID del Almacén",
            value = id,
            onValueChange = { id = it },
            onlyNumber = true
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
                    id.isBlank() -> errorMessage = "El campo 'ID' no puede estar vacío."
                    else -> {
                        // Campos válidos, procesar la transacción
                        viewModel.consultarTransacciones(id.toInt())
                        errorMessage = null // Limpiar el mensaje de error después del éxito
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Consultar transacciones")
        }

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(transacciones) { transaccion ->
                Column(modifier = Modifier) {
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 5.dp)
                            .padding(horizontal = 10.dp),
                        text = "Nombre del Producto:",
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 10.dp)
                            .padding(horizontal = 10.dp),
                        text = transaccion.productoNombre,
                        fontSize = 18.sp
                    )
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Tipo: ${transaccion.tipo}",
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Cantidad: ${transaccion.cantidad}",
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Fecha: ${transaccion.fecha}",
                        fontSize = 20.sp
                    )
                }
                HorizontalDivider(modifier = Modifier.padding(10.dp), thickness = 2.dp)
            }
        }

    }
}

