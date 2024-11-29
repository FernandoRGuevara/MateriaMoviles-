package com.example.almacenes.ui.components

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Text
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.zxing.integration.android.IntentIntegrator

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun QRScanner(
    onQRCodeScanned: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var hasCameraPermission by remember { mutableStateOf(false) }

    // Request camera permission
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    // When the permission changes
    LaunchedEffect(permissionState) {
        if (permissionState.status.isGranted) {
            hasCameraPermission = true
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    // If the permission is granted, scan the QR
    if (hasCameraPermission) {
        val qrScannerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result.data
            val qrContent = IntentIntegrator.parseActivityResult(result.resultCode, data)?.contents
            if (qrContent != null) {
                onQRCodeScanned(qrContent)
            } else {
                Log.e("QRScanner", "No QR code found")
            }
        }

        Box(modifier = modifier) {
            Text(text = "Escanea el código QR")
            DisposableEffect(Unit) {
                val integrator = IntentIntegrator(context as androidx.activity.ComponentActivity)
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                integrator.setPrompt("Scan QR Code")
                integrator.setCameraId(0)
                integrator.setBeepEnabled(true)
                integrator.setBarcodeImageEnabled(false)

                qrScannerLauncher.launch(integrator.createScanIntent())
                onDispose { }
            }
        }
    } else {
        // Si no tiene permiso
        Text(text = "Permiso de cámara requerido")
    }
}