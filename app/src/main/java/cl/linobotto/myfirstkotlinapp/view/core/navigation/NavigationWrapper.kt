package cl.linobotto.myfirstkotlinapp.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.linobotto.myfirstkotlinapp.view.home.HomeScreen

@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home){
        composable<Home> {
            HomeScreen()
        }
    }
}