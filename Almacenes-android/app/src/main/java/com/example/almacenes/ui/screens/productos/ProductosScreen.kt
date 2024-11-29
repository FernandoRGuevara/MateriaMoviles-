package com.example.almacenes.ui.screens.productos

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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.almacenes.data.model.ExistenciaProducto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(
    navController: NavController,
    productosViewModel: ProductosViewModel = hiltViewModel() // Se obtiene el ViewModel usando hiltViewModel()
) {
    val productos by productosViewModel.productos.observeAsState(emptyList())

    val existencias by productosViewModel.existencias.observeAsState(emptyList())


    var visible by remember { mutableStateOf(false) }

    var id by remember { mutableIntStateOf(1) }


    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(
            title = { Text("Todos los productos: ") }
        )

        // Mostrar las existencias en una lista
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(productos) { producto ->
                // Estado local para almacenar la cantidad específica de este producto
                val cantidad = remember { mutableStateOf("Cargando...") }

                // Cargar el valor específico para este ítem
                LaunchedEffect(producto.id) {
                    val existencia = productosViewModel.obtenerCantidadSuspend(producto.id)
                    cantidad.value = if (existencia.isNotEmpty()) {
                        "${obtenerCantidad(existencia)}"
                    } else {
                        "Sin existencias"
                    }
                }

                Column(
                    modifier = Modifier
                        .clickable {
                            id = producto.id
                            visible = true
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "UPC: ${producto.upc}",
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Descripción: ${producto.descripcion}",
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Cantidad: ${cantidad.value}",
                        fontSize = 20.sp
                    )
                }
                HorizontalDivider(modifier = Modifier.padding(10.dp), thickness = 2.dp)
            }
        }

    }

    //visible = UnidadesDisponibles(id = id, visible = visible, existencias = existencias)

    when {
        visible -> {
            productosViewModel.obtenerExistencias(id)
            CustomConfirmDialog(
                existencias = existencias,
                onDismissDialog = {
                    visible = false
                }
            )
        }
    }
}

fun obtenerCantidad(list: List<ExistenciaProducto>): Int {
    var suma = 0
    for (i in list) {
        suma += i.cantidad
    }
    return suma
}

@Composable
fun UnidadesDisponibles(id: Int, visible: Boolean, existencias: List<ExistenciaProducto>): Boolean {
    var visible by remember { mutableStateOf(visible) }
    if (visible) {
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(existencias) { existencia ->
                Column(modifier = Modifier) {
                    Text(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Nombre del almacen: ${existencia.almacenNombre}",
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
                }
                HorizontalDivider(modifier = Modifier.padding(10.dp), thickness = 2.dp)
            }
        }
    }
    return visible
}
