package cl.linobotto.myfirstkotlinapp.view.home

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionDos
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionTres
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionUno
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionCuatro
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionCinco
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionSeis
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionSiete
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionOcho
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionNueve
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionDiez
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionOnce

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavController
) {

    //puede usar by para limpiar el estado o usar un = pero para llamar el estado seria uiState.value
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Catalogo de lecciones"
            )
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.kodee_petite),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Button(
                    onClick = { navController.navigate(LeccionUno) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "1. Importación de librerias")
                }
                Button(
                    onClick = { navController.navigate(LeccionDos) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "2. Importación de plugins")
                }
                Button(
                    onClick = { navController.navigate(LeccionTres) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "3. Composables Boxes")
                }
                Button(
                    onClick = { navController.navigate(LeccionCuatro) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "4. Composables Row y Column")
                }
                Button(
                    onClick = { navController.navigate(LeccionCinco) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "5. Composable Spacer")
                }
                Button(
                    onClick = { navController.navigate(LeccionSeis) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "6. ConstraintLayout")
                }
                Button(
                    onClick = { navController.navigate(LeccionSiete) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "7. Estados y Recomposición")
                }
                Button(
                    onClick = { navController.navigate(LeccionOcho) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "8. Text y TextField")
                }
                Button(
                    onClick = { navController.navigate(LeccionNueve) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "9. MVVM")
                }
                Button(
                    onClick = { navController.navigate(LeccionDiez) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "10. Scaffold")
                }
                Button(
                    onClick = { navController.navigate(LeccionOnce) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "11. Image e Icon")
                }
            }
        }
    }
}