package com.juandeherrera.reciclafacil.screens

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.juandeherrera.reciclafacil.metodosAuxiliares.CameraScannerView
import com.juandeherrera.reciclafacil.navigation.AppScreens
import kotlinx.coroutines.launch

@Composable
fun BarcodeScannerScreen(controladorNavegacion: NavHostController) {
    val context = LocalContext.current
    val activity = context as Activity

    // Pedimos permiso de cámara al entrar en la pantalla
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.CAMERA),
                100
            )
        }
    }

    // Estado del Snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // Scope para lanzar coroutines (necesario para mostrar Snackbars)
    val scope = rememberCoroutineScope()

    // Estado donde guardaremos el código detectado
    var codigoDetectado by remember { mutableStateOf<String?>("prueba") }

    var mensaje = ""

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Vista de la cámara + análisis de códigos
            CameraScannerView(
                modifier = Modifier.weight(1f)
            ) { codigo ->

                if(codigo.equals("8431876267259")){
                    mensaje = "Las botellas de plástico van en el contenedor amarillo."
                }
                else{
                    mensaje = "Deshecho no encontrado."
                }

                // Solo se ejecuta si el código detectado no es igual al que ya hay en el snackbar
                if(!mensaje.equals(snackbarHostState.currentSnackbarData?.visuals?.message)){
                    snackbarHostState.currentSnackbarData?.dismiss()

                    // Mostramos el Snackbar
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = mensaje,
                            actionLabel = "Ver más detalles", // Botón del snackbar
                            duration = SnackbarDuration.Indefinite, // El snackbar dura hasta que se presiona la X o se escanea un nuevo código
                            withDismissAction = true
                        )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                controladorNavegacion.navigate(AppScreens.producto.route + "/2") // al pulsar en el producto se navega a su pantalla
                            }

                            SnackbarResult.Dismissed -> {
                                /* Handle snackbar dismissed */
                            }

                        }
                    }

                }
            }
        }
    }
}