package com.juandeherrera.reciclafacil.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import com.juandeherrera.reciclafacil.localdb.AppDB
import com.juandeherrera.reciclafacil.localdb.Estructura
import com.juandeherrera.reciclafacil.metodosAuxiliares.tarjetaProductoHistorial
import com.juandeherrera.reciclafacil.metodosAuxiliares.tarjetaSinProductos
import com.juandeherrera.reciclafacil.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaHistorial(controladorNavegacion: NavController) {

    val context =
        LocalContext.current // se obtiene el contexto actual (necesario para la bd local y mostrar mensajes Toasts)

    // instancia a la base de datos local
    // se indica el contexto, nombre del archivo, permitiendo operaciones en el hilo principal
    // con allowMainThreadQueries() se hace que el manejo de la base de datos y la app corran en el mismo hilo (no es lo mas recomendable)
    val db = Room.databaseBuilder(context, AppDB::class.java, Estructura.DB.NAME)
        .allowMainThreadQueries().build()

    val usuario =
        db.sesionDao().obtenerUsuario()  // se obtiene los datos del usuario que tiene sesion activa

    val datos = remember {
        db.historialDao().obtenerHistorial(usuario.idUsuario).toMutableStateList()
    } // lista de registros del historial

    Scaffold(
        // BARRA SUPERIOR
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(100.dp),       // altura de la barra superior
                title = {
                    Text(
                        text = "Historial", // texto del titulo de la barra superior
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
                NavigationBarItem(
                    selected = false,
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
                NavigationBarItem(
                    selected = true,
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
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()                 // ocupa el espacio disponible
                .padding(innerPadding)         // usa el padding por defecto
                .background(Color(0xFFE0F8D9)),       // color de fondo
            horizontalAlignment = Alignment.CenterHorizontally,   // centrado horizontal
        ) {
            // FILAS CON LOS PRODUCTOS RECICLADOS POR EL USUARIO
            LazyColumn {
                // se comprueba que haya productos en el historial
                if (datos.isEmpty()) {
                    // lista de un solo item a mostrar
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),              // ocupa el ancho de la pantalla
                            verticalAlignment = Alignment.CenterVertically,  // centrado vertical
                            horizontalArrangement = Arrangement.Center       // centrado horizontal
                        ) {
                            tarjetaSinProductos() // se invoca la tarjeta
                        }
                    }
                } else {
                    // lista indexada que contiene los productos del historial
                    items(
                        items = datos,
                        key = { it.idHistorial } // clave unica para identificar cada item
                    ) { registro ->

                        // define el estado del deplazamiento
                        val estadoDesplazamiento = rememberSwipeToDismissBoxState(
                            confirmValueChange = { valor ->
                                // si el usuario desliza la tarjeta de izquierda a derecha
                                if (valor == SwipeToDismissBoxValue.StartToEnd) {

                                    db.historialDao()
                                        .eliminarRegistro(registro.idHistorial)  // eliminar registro de la base de datos

                                    datos.remove(registro) // se elimina el producto de la lista

                                    true // se confirma que la accion se realizo
                                } else {
                                    false // si desliza hacia otro lada, no se hace nada
                                }
                            }
                        )

                        // box que hace de marco del elemento al aplicarle el padding y que el fondo rojo quede contenido
                        Box(
                            modifier = Modifier.padding(
                                vertical = 10.dp,
                                horizontal = 16.dp
                            ) // padding en los laterales horizontales y verticales
                        ) {
                            SwipeToDismissBox(
                                state = estadoDesplazamiento,
                                enableDismissFromStartToEnd = true,  // se activa el deslizamiento hacia la derecha
                                enableDismissFromEndToStart = false, // se desactiva el deslizamiento hacia la izquierda

                                // define que hay detras de la tarjeta (el contenido oculto)
                                backgroundContent = {

                                    // contenedor visual del fondo (la capa roja con el icono)
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()  // ocupa el espacio disponible
                                            .background(
                                                Color.Red,
                                                shape = CardDefaults.shape
                                            ) // color de fondo con borde redondeado
                                            .padding(horizontal = 16.dp), // padding interno
                                        contentAlignment = Alignment.CenterStart // centrado al centro a la derecha
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete, // icono
                                            contentDescription = "Borrar",      // descripcion del icono
                                            tint = Color.White                  // color del icono
                                        )
                                    }
                                }
                            ) {
                                // contenido frontal (lo que ve el usuario y se mueve)

                                val producto = db.productoDao()
                                    .getProducto(registro.idProductoVisitado) // obtenemos el producto asociado al contenido del registro

                                tarjetaProductoHistorial(producto) // se invoca la tarjeta
                            }
                        }

                    }
                }
            }
        }
    }
}