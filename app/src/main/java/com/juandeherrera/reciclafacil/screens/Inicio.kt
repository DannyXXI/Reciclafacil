package com.juandeherrera.reciclafacil.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import com.juandeherrera.reciclafacil.R
import com.juandeherrera.reciclafacil.localdb.AppDB
import com.juandeherrera.reciclafacil.localdb.Estructura
import com.juandeherrera.reciclafacil.metodosAuxiliares.TendenciaCard
import com.juandeherrera.reciclafacil.metodosAuxiliares.VideoCard
import com.juandeherrera.reciclafacil.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(controladorNavegacion: NavController) {

    val context = LocalContext.current // se obtiene el contexto actual (necesario para la bd local y mostrar mensajes Toasts)

    // instancia a la base de datos local
    // se indica el contexto, nombre del archivo, permitiendo operaciones en el hilo principal
    // con allowMainThreadQueries() se hace que el manejo de la base de datos y la app corran en el mismo hilo (no es lo mas recomendable)
    val db = Room.databaseBuilder(context, AppDB::class.java, Estructura.DB.NAME).allowMainThreadQueries().build()

    Scaffold(
        // BARRA SUPERIOR
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(100.dp),       // altura de la barra superior
                title = {
                    Text(
                        text = "Reciclafacil", // texto del titulo de la barra superior
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,  // fuente tipografica del titulo
                            fontSize = 28.sp,                    // tamaño de fuente del titulo
                        )
                    )
                },
                colors = topAppBarColors(
                    containerColor = Color(0xFF34BB00),   // color de fondo de la barra superior
                    titleContentColor = Color.White              // color del texto del titulo de la barra superior
                )
            )
        },
        // BARRA INFERIOR
        bottomBar = {
            // BARRA DE NAVEGACION
            NavigationBar {
                // Inicio
                NavigationBarItem(
                    selected = true,
                    onClick = {
                        controladorNavegacion.navigate(AppScreens.inicio.route) // se navega a la opcion de inicio
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,  // icono
                            contentDescription = "inicio",      // descripcion del icono
                            modifier = Modifier.size(30.dp)    // tamaño del icono
                        )
                    },
                    label = {
                        Text(
                            text = "Inicio",    // texto
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,  // fuente tipografica del texto
                                fontSize = 14.sp,                   // tamaño de fuente del texto
                                fontWeight = FontWeight.Bold        // texto con negrita
                            )
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Green,       // color del icono al ser seleccionado
                        selectedTextColor = Color.Green,       // color del texto al ser seleccionado
                        indicatorColor = Color(0xFFCEFFD1), // color de fondo del item seleccionado
                        unselectedIconColor = Color.Black,     // color del icono no seleccionado
                        unselectedTextColor = Color.Black      // color del texto no seleccionado
                    )
                )

                // Búsqueda
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        controladorNavegacion.navigate(AppScreens.busqueda.route) // se navega a la opcion de busqueda
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,  // icono
                            contentDescription = "buscar",      // descripcion del icono
                            modifier = Modifier.size(30.dp)    // tamaño del icono
                        )
                    },
                    label = {
                        Text(
                            text = "Busqueda",    // texto
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,  // fuente tipografica del texto
                                fontSize = 14.sp                   // tamaño de fuente del texto
                            )
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Green,       // color del icono al ser seleccionado
                        selectedTextColor = Color.Green,       // color del texto al ser seleccionado
                        indicatorColor = Color(0xFFCEFFD1), // color de fondo del item seleccionado
                        unselectedIconColor = Color.Black,     // color del icono no seleccionado
                        unselectedTextColor = Color.Black      // color del texto no seleccionado
                    )
                )

                // Escáner
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        controladorNavegacion.navigate(AppScreens.escaner.route) // se navega a la opcion de escaner
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.PhotoCamera,  // icono
                            contentDescription = "escaner",      // descripcion del icono
                            modifier = Modifier.size(30.dp)    // tamaño del icono
                        )
                    },
                    label = {
                        Text(
                            text = "Escáner",    // texto
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,  // fuente tipografica del texto
                                fontSize = 14.sp                   // tamaño de fuente del texto
                            )
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Green,       // color del icono al ser seleccionado
                        selectedTextColor = Color.Green,       // color del texto al ser seleccionado
                        indicatorColor = Color(0xFFCEFFD1), // color de fondo del item seleccionado
                        unselectedIconColor = Color.Black,     // color del icono no seleccionado
                        unselectedTextColor = Color.Black      // color del texto no seleccionado
                    )
                )

                // Historial
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        controladorNavegacion.navigate(AppScreens.historial.route) // se navega a la opcion de historial
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.History,  // icono
                            contentDescription = "historial",      // descripcion del icono
                            modifier = Modifier.size(30.dp)    // tamaño del icono
                        )
                    },
                    label = {
                        Text(
                            text = "Historial",    // texto
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,  // fuente tipografica del texto
                                fontSize = 14.sp                   // tamaño de fuente del texto
                            )
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Green,       // color del icono al ser seleccionado
                        selectedTextColor = Color.Green,       // color del texto al ser seleccionado
                        indicatorColor = Color(0xFFCEFFD1), // color de fondo del item seleccionado
                        unselectedIconColor = Color.Black,     // color del icono no seleccionado
                        unselectedTextColor = Color.Black      // color del texto no seleccionado
                    )
                )

                // Perfil
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        controladorNavegacion.navigate(AppScreens.perfil.route) // se navega a la opcion de perfil
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,  // icono
                            contentDescription = "perfil",      // descripcion del icono
                            modifier = Modifier.size(30.dp)    // tamaño del icono
                        )
                    },
                    label = {
                        Text(
                            text = "Perfil",    // texto
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,  // fuente tipografica del texto
                                fontSize = 14.sp                   // tamaño de fuente del texto
                            )
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Green,       // color del icono al ser seleccionado
                        selectedTextColor = Color.Green,       // color del texto al ser seleccionado
                        indicatorColor = Color(0xFFCEFFD1), // color de fondo del item seleccionado
                        unselectedIconColor = Color.Black,     // color del icono no seleccionado
                        unselectedTextColor = Color.Black      // color del texto no seleccionado
                    )
                )
            }
        }
    ){
        innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize()      // ocupa el espacio disponible
                .padding(innerPadding)     // usa el padding por defecto
                .background(Color(0xFFE0F8D9))     // color de fondo
                .verticalScroll(rememberScrollState()), // scroll vertical
            horizontalAlignment = Alignment.CenterHorizontally,       // centrado horizontal
            verticalArrangement = Arrangement.spacedBy(16.dp) // separacion entre elementos verticalmente
        ){
            VideoCard("videoreciclaje")

            // titulo para la parte de tendencias
            Text(
                text = "Tendencias",  // texto
                color = Color(0xFF2E7D32),  // color del texto
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,  // fuente tipografica
                    textAlign = TextAlign.Left, // texto alineado en el centro
                    fontSize = 20.sp,           // tamaño de fuente del texto
                    fontWeight = FontWeight.Bold, // texto en negrita
                ),
                modifier = Modifier.fillMaxWidth() // ocupa el ancho posible
                    .padding(horizontal = 16.dp)   // padding interno
            )

            Row(
                modifier = Modifier.fillMaxWidth() // ocupa el ancho disponible
                    .padding(16.dp),          // padding interno
                horizontalArrangement = Arrangement.SpaceBetween, // separacion entre elementos a la misma distancia horizontalmente
                verticalAlignment = Alignment.CenterVertically    // centrado vertical
            ){
                TendenciaCard(controladorNavegacion, R.drawable.botellasplastico, "Plástico", db.productoDao().getProducto(2))

                TendenciaCard(controladorNavegacion, R.drawable.pilas, "Pilas", db.productoDao().getProducto(1))

                TendenciaCard(controladorNavegacion, R.drawable.papelcarton, "Papel", db.productoDao().getProducto(3))
            }
        }
    }
}