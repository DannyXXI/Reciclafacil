package com.juandeherrera.reciclafacil.metodosAuxiliares

import android.annotation.SuppressLint
import android.net.Uri
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

// metodo auxiliar para obtener la tarjeta con el video en local
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun VideoCard(nombreVideo: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()  // abarca el ancho disponible
            .padding(16.dp),           // padding interno
        shape = RoundedCornerShape(16.dp),     // bordes redondeados
        elevation = CardDefaults.cardElevation(8.dp)  // sombreado
    ){
        // permite usar vistas clasicas (View) dentro de Jetpack Compose
        AndroidView(
            factory = { context ->
                // se crea una vista de video nativa de Android
                VideoView(context).apply {
                    val mediaController = MediaController(context) // controlador del reproductor

                    mediaController.setAnchorView(this) // se ancla a la propia vista de video (VideoView)

                    setMediaController(mediaController) // se asigna a la vista de video (VideoView)

                    // se define la URI del video a reproducir (dentro de la carpeta res/raw)
                    val videoUri = Uri.parse("android.resource://${context.packageName}/raw/${nombreVideo}")

                    setVideoURI(videoUri) // se asigna la URI a la vista de visdeo (VideoView)

                    // si quisieramos iniciar el video automaticamente -> start()

                    // escuchador que se ejecuta cuando el video esta listo
                    setOnPreparedListener { mp ->
                        mp.isLooping = false  // se indica que video no estara en loop
                        mp.setVideoScalingMode(android.media.MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT) // ajusta la escala del video dentro de la vista sin deformarlo
                    }

                    visibility = View.VISIBLE // hace que la vista sea visible desde el inicio
                }
            },
            modifier = Modifier.fillMaxWidth() // el video ocupara el ancho disponible
        )
    }
}

