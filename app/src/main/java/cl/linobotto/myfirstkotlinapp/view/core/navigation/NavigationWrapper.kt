package cl.linobotto.myfirstkotlinapp.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.LeccionDiezScreen
import cl.linobotto.myfirstkotlinapp.view.home.lecciones.LeccionOnceScreen

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
    }
}