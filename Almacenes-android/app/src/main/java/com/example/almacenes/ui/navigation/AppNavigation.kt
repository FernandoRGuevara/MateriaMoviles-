package com.example.almacenes.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.almacenes.ui.screens.ExistenciaPorAlmacenScreen
import com.example.almacenes.ui.screens.HomeScreen
import com.example.almacenes.ui.screens.RegistrarTransaccionScreen
import com.example.almacenes.ui.screens.almacenes.AlmacenesScreen
import com.example.almacenes.ui.screens.consultarTransacciones.ConsultarTransaccionScreen
import com.example.almacenes.ui.screens.productos.ProductosScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.fillMaxSize()
    ) {
        // Composable para la pantalla Home
        composable("home") {
            HomeScreen(
                navController
            )
        }

        composable("productos") {
            ProductosScreen(
                navController
            )
        }

        composable("consultar_transaccion") {
            ConsultarTransaccionScreen(
                navController
            )
        }

        // Composable para registrar transacción
        composable("registrar") {
            RegistrarTransaccionScreen()
        }

        composable("almacenes") {
            AlmacenesScreen(navController)
        }

        // Composable para mostrar existencias por almacén
        composable("existencias/{almacenId}") { backStackEntry ->
            val almacenId = backStackEntry.arguments?.getString("almacenId")?.toLong() ?: 0L
            ExistenciaPorAlmacenScreen(
                navController = navController,
                almacenId = almacenId,
            )
        }
    }
}