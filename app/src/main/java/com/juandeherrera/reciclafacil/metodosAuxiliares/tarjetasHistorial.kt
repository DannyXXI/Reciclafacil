package com.juandeherrera.reciclafacil.metodosAuxiliares

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.Frown
import com.composables.icons.lucide.Lucide
import com.juandeherrera.reciclafacil.localdb.ProductoData

// funcion auxiliar para mostar la tarjeta que indica al usuario que el historial esta vacio
@Composable
fun tarjetaSinProductos() {
    Card(
        modifier = Modifier.fillMaxWidth() // ocupa el ancho de la pantalla
            .height(90.dp)         // altura
            .padding(16.dp),  // padding interno
        colors = CardDefaults.cardColors(
            containerColor = Color.White  // color de fondo de la tarjeta
        ),
        elevation = CardDefaults.cardElevation(4.dp) // sombreado de elevacion de la tarjeta
    ){
        Row(
            modifier = Modifier.fillMaxSize() // ocupa el espacio de la tarjeta
                .padding(horizontal = 16.dp),  // padding en los laterales
            horizontalArrangement = Arrangement.Center,    // centrado horizontal
            verticalAlignment = Alignment.CenterVertically // centrado vertical
        ){

            Text(
                text = "No hay productos reciclados ",  // texto
                color = Color.Black,  // color del texto
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,  // fuente tipografica
                    textAlign = TextAlign.Center, // texto alineado en el centro
                    fontSize = 20.sp,  // tamaño de fuente del texto
                )
            )

            Icon(
                imageVector = Lucide.Frown,  // icono
                contentDescription = "Triste", // descripcion del icono
                tint = Color.Black,            // color del icono
                modifier = Modifier.size(20.dp)  // tamaño del icono
            )

        }
    }
}

// funcion auxiliar para la tarjeta del producto del historial
@Composable
fun tarjetaProductoHistorial(producto: ProductoData?) {

    Card(
        modifier = Modifier.fillMaxWidth() // ocupa el ancho de la pantalla
            .height(80.dp),       // altura
        colors = CardDefaults.cardColors(
            containerColor = Color.White  // color de fondo de la tarjeta
        ),
        elevation = CardDefaults.cardElevation(4.dp) // sombreado de elevacion de la tarjeta
    ){
        Row(
            modifier = Modifier.fillMaxSize() // ocupa el espacio de la tarjeta
                .padding(horizontal = 16.dp),  // padding en los laterales
            verticalAlignment = Alignment.CenterVertically // centrado vertical
        ){
            // ICONO DEL PRODUCTO DEL HISTORIAL
            Icon(
                imageVector = Icons.Default.Eco,        // icono
                contentDescription = "Hoja eco",        // descripcion del icono
                tint = Color(0xFF34BB00),        // color del icono
                modifier = Modifier.size(34.dp)   // tamaño del icono
            )

            Spacer(modifier = Modifier.width(12.dp))  // espaciado horizontal entre componentes

            Column {
                Text(
                    text = producto!!.tituloProducto,  // texto
                    color = Color.Black,  // color del texto
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,  // fuente tipografica
                        fontSize = 18.sp,  // tamaño de fuente del texto
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))  // espaciado vertical entre componentes

                Text(
                    text = "Reciclado en el ${producto.contenedorProducto.lowercase()}.",  // texto
                    color = Color.Black,  // color del texto
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,  // fuente tipografica
                        fontSize = 13.sp  // tamaño de fuente del texto
                    )
                )
            }
        }
    }

}