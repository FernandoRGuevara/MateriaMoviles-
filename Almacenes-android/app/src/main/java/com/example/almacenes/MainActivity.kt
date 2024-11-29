package com.example.almacenes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.almacenes.ui.navigation.AppNavigation
import com.example.almacenes.ui.screens.ExistenciaPorAlmacenScreen
import com.example.almacenes.ui.screens.ExistenciaViewModel
import com.example.almacenes.ui.theme.AlmacenesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Anotación para habilitar Hilt en la Activity
class MainActivity : ComponentActivity() {

    // Usamos la propiedad 'viewModels' para obtener el ViewModel que ya está inyectado por Hilt
    private val existenciaViewModel: ExistenciaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            // Aquí pasas el ViewModel a la UI
            AppNavigation(navController = navController)
        }
    }
}