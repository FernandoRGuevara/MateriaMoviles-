package com.example.almacenes.ui.screens.almacenes

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlmacenesScreen(
    navController: NavController,
    almacenesViewModel: AlmacenesViewModel = hiltViewModel() // Se obtiene el ViewModel usando hiltViewModel()
) {
    val almacenes by almacenesViewModel.almacenes.observeAsState(emptyList())

    var visible by remember { mutableStateOf(false) }

    var id by remember { mutableIntStateOf(1) }


    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(
            title = { Text("Todos los almacenes: ") }
        )

        // Mostrar las existencias en una lista
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(almacenes) { almacen ->

                Column(
                    modifier = Modifier
                        .clickable {
                            navController.navigate("existencias/${almacen.id}")
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "ID: ${almacen.id}",
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Nombre: ${almacen.nombre}",
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Dirección: ${almacen.direccion}",
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Latitud: ${almacen.latitud}",
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Longitud: ${almacen.longitud}",
                        fontSize = 20.sp
                    )
                }
                HorizontalDivider(modifier = Modifier.padding(10.dp), thickness = 2.dp)
            }
        }
    }
}

