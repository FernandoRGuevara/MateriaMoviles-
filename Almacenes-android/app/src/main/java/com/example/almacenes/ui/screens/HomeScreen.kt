package com.example.almacenes.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(
            title = { Text("Aplicación de Almacenes") }
        )

        Button(onClick = {
            navController.navigate("almacenes")
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Ver almacenes")
        }

        Button(onClick = {
            navController.navigate("consultar_transaccion")
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Ver Transacciones por Almacén")
        }

        Button(
            onClick = { navController.navigate("productos") },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Ver Productos")
        }

        Button(onClick = {
            navController.navigate("registrar")
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Registrar Transacción")
        }
    }
}