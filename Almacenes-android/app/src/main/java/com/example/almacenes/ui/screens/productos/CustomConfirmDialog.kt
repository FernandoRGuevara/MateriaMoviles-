package com.example.almacenes.ui.screens.productos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.almacenes.R
import com.example.almacenes.data.model.ExistenciaProducto

@Composable
fun CustomConfirmDialog(
    modifier: Modifier = Modifier,
    existencias: List<ExistenciaProducto>,
    onDismissDialog: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissDialog,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Gray
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Box {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = "Todas las existencias del producto:",
                    fontSize = 20.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.x_close),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onDismissDialog() }
                        .padding(top = 10.dp, end = 10.dp)
                        .align(Alignment.TopEnd)
                        .size(24.dp),
                    tint = Color.Black
                )
            }

            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                items(existencias) { existencia ->
                    Column(modifier = Modifier) {
                        Text(
                            modifier = Modifier
                                .height(40.dp)
                                .fillMaxWidth()
                                .padding(10.dp),
                            text = "Nombre del almacen: ${existencia.almacenNombre}",
                            fontSize = 12.sp
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
    }
}
