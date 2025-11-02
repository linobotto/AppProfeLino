package cl.linobotto.myfirstkotlinapp.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cl.linobotto.myfirstkotlinapp.view.home.HomeScreen
import cl.linobotto.myfirstkotlinapp.view.home.HomeViewModel
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.LeccionCincoScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.LeccionDosScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.LeccionTresScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.LeccionUnoScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.LeccionCuatroScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.LeccionSeisScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.LeccionSieteScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.LeccionOchoScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_nueve.LeccionNueveScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_diez.LeccionDiezScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_once.LeccionOnceScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_doce.LeccionDoceScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_trece.LeccionTreceScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_trece.LeccionTreceDetalleScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_catorce.LeccionCatorceScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_quince.LeccionQuinceScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_dieciseis.LeccionDieciseisScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_diecisiete.LeccionDiecisieteScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_dieciocho.LeccionDieciochoScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_diecinueve.LeccionDiecinueveScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veinte.LeccionVeinteScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintidos.LeccionVeintidosScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintiuno.LeccionVeintiunoScreen

@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home){
        composable<Home> {
            HomeScreen(
                homeViewModel = HomeViewModel(),
                navController = navController
            )
        }
        composable<LeccionUno> {
            LeccionUnoScreen(
                navController = navController
            )
        }
        composable<LeccionDos> {
            LeccionDosScreen(
                navController = navController
            )
        }
        composable <LeccionTres> {
            LeccionTresScreen(
                navController = navController
            )
        }
        composable <LeccionCuatro> {
            LeccionCuatroScreen(
                navController = navController
            )
        }
        composable <LeccionCinco> {
            LeccionCincoScreen(
                navController = navController
            )
        }
        composable <LeccionSeis> {
            LeccionSeisScreen(
                navController = navController
            )
        }
        composable <LeccionSiete> {
            LeccionSieteScreen(
                navController = navController
            )
        }
        composable <LeccionOcho> {
            LeccionOchoScreen(
                navController = navController
            )
        }
        composable <LeccionNueve> {
            LeccionNueveScreen(
                navController = navController
            )
        }
        composable <LeccionDiez> {
            LeccionDiezScreen(
                navController = navController
            )
        }
        composable <LeccionOnce> {
            LeccionOnceScreen(
                navController = navController
            )
        }
        composable <LeccionDoce> {
            LeccionDoceScreen(
                navController = navController
            )
        }
        composable <LeccionTrece> {
            LeccionTreceScreen(
                navController = navController
            )
        }
        composable<LeccionTreceDetalle> { backStackEntry ->
            val detalle = backStackEntry.toRoute<LeccionTreceDetalle>()
            LeccionTreceDetalleScreen(
                navController = navController,
                backStackArgs = detalle
            )
        }
        composable <LeccionCatorce> {
            LeccionCatorceScreen(
                navController = navController
            )
        }
        composable <LeccionQuince> {
            LeccionQuinceScreen(
                navController = navController
            )
        }
        composable <LeccionDieciseis> {
            LeccionDieciseisScreen(
                navController = navController
            )
        }
        composable <LeccionDiecisiete> {
            LeccionDiecisieteScreen(
                navController = navController
            )
        }
        composable <LeccionDieciocho> {
            LeccionDieciochoScreen(
                navController = navController
            )
        }
        composable <LeccionDiecinueve> {
            LeccionDiecinueveScreen(
                navController = navController
            )
        }
        composable <LeccionVeinte> {
            LeccionVeinteScreen(
                navController = navController
            )
        }
        composable <LeccionVeintiuno>{
            LeccionVeintiunoScreen(
                navController = navController
            )
        }
        composable <LeccionVeintidos>{
            LeccionVeintidosScreen(
                navController = navController
            )
        }
    }
}