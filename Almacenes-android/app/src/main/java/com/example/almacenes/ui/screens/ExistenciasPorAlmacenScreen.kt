package com.example.almacenes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistenciaPorAlmacenScreen(
    navController: NavController,
    almacenId: Long,
    existenciaViewModel: ExistenciaViewModel = hiltViewModel() // Se obtiene el ViewModel usando hiltViewModel()
) {
    val existencias by existenciaViewModel.existencias.observeAsState(emptyList())

    LaunchedEffect(true) {
        existenciaViewModel.getExistenciasByAlmacen(almacenId)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(
            title = { Text("Existencias en el Almacén: $almacenId") }
        )
        // Mostrar las existencias en una lista
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(existencias) { existencia ->
                Text(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = "Nombre: ${existencia.nombreProducto}",
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = "Cantidad: ${existencia.cantidad}",
                    fontSize = 20.sp
                )
                HorizontalDivider(modifier = Modifier.padding(10.dp), thickness = 2.dp)
            }
        }
    }
}

