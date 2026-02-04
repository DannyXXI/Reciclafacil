package com.juandeherrera.reciclafacil.metodosAuxiliares

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.juandeherrera.reciclafacil.localdb.ProductoData
import com.juandeherrera.reciclafacil.navigation.AppScreens

// metodo auxiliar para crear las tarjetas de la categoria de tendencias
@Composable
fun TendenciaCard (controladorNavegacion: NavController, imagenProducto: Int, titulo: String, producto: ProductoData?) {

    Card(
        modifier = Modifier.width(110.dp) // ancho de la tarjeta
            .clickable{
                controladorNavegacion.navigate(AppScreens.producto.route + "/${producto!!.idProducto}") // al pulsar en el producto se navega a su pantalla
            },
        shape = RoundedCornerShape(12.dp),     // bordes redondeados
        elevation = CardDefaults.cardElevation(4.dp)  // sombreado
    ){
        Column{
            // imagen de la tarjeta
            Image(
                painter = painterResource(id = imagenProducto), // id de la imagen en local
                contentDescription = titulo,                    // descripcion de la imagen
                modifier = Modifier.fillMaxWidth() // abarca el ancho disponible
                    .height(80.dp), // altura de la imagen
                contentScale = ContentScale.Crop // la imagen ocupa la tarjeta
            )

            // texto de la tarjeta
            Text(
                text = titulo,  // texto
                color = Color.Black,  // color del texto
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,  // fuente tipografica
                    textAlign = TextAlign.Center, // texto alineado en el centro
                    fontSize = 16.sp,  // tamaño de fuente del texto
                    fontWeight = FontWeight.Bold, // texto en negrita
                ),
                modifier = Modifier.padding(8.dp)  // padding interno
            )



        }




    }




}