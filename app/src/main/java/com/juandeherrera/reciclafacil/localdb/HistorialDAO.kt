package com.juandeherrera.reciclafacil.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// OPERACIONES QUE SE REALIZARAN EN LA TABLA USUARIO
@Dao
interface HistorialDAO {
    // OBTENER EL HISTORIAL DE UN USUARIO
    @Query("SELECT * FROM ${Estructura.Historial.TABLE_NAME} WHERE ${Estructura.Historial.ID_USUARIO} = :idUsuario")
    fun obtenerHistorial(idUsuario: Int): List<HistorialData>

    // AGREGAR UN NUEVO PRODUCTO AL HISTORIAL
    @Insert
    fun nuevoRegistro (historialData: HistorialData)

    // ELIMINAR UN PRODUCTO DEL HISTORIAL
    @Query("DELETE FROM ${Estructura.Historial.TABLE_NAME} WHERE ${Estructura.Historial.ID} = :idRegistro")
    fun eliminarRegistro(idRegistro: Int)
}