package cl.linobotto.myfirstkotlinapp.view.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object LeccionUno

@Serializable
object LeccionDos

@Serializable
object LeccionTres

@Serializable
object LeccionCuatro

@Serializable
object LeccionCinco

@Serializable
data object LeccionDiez

@Serializable
data object LeccionSeis

@Serializable
data object LeccionSiete

@Serializable
data object LeccionOcho

@Serializable
data object LeccionNueve

@Serializable
data object LeccionOnce

@Serializable
data object LeccionDoce

// Lección 13: Destinos para demostrar navegación y paso de argumentos serializables
@Serializable
data object LeccionTrece

@Serializable
data class LeccionTreceDetalle(val nombre: String, val edad: Int)

@Serializable
 data object LeccionCatorce

 @Serializable
 data object LeccionQuince
 
@Serializable
 data object LeccionDieciseis

@Serializable
 data object LeccionDiecisiete

@Serializable
 data object LeccionDieciocho

@Serializable
 data object LeccionDiecinueve

@Serializable
 data object LeccionVeinte
