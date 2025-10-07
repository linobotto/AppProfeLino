package cl.linobotto.myfirstkotlinapp.view.home.lecciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionTrece
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionTreceDetalle

@Preview(showBackground = true, heightDp = 2000)
@Composable
fun LeccionTrecePreview() {
    LeccionTreceScreen(navController = rememberNavController())
}

@Composable
fun LeccionTreceScreen(navController: NavController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "13. Navigation: NavController, NavHost y paso de argumentos"
            )
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.kodee_sitting),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                IntroNavigation()
                Spacer(modifier = Modifier.height(16.dp))
                CodigoNavHost()
                Spacer(modifier = Modifier.height(16.dp))
                EjemploPasoDeArgumentos(navController)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate(Home) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Volver al Home")
                }
            }
        }
    }
}

@Composable
private fun IntroNavigation() {
    Column {
        Text(
            text = "Navigation Compose te permite moverte entre pantallas (destinos) usando un NavController y un NavHost. En este proyecto usamos rutas tipadas con kotlinx.serialization." +
                    "\n\nPrimero agreguemos lo necesario:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append("• NavController: ")
                }
                append("orquesta la navegación.\n")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append("• NavHost: ")
                }
                append(" contiene los destinos (composable) y su ruta inicial.\n")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append("• @Serializable:")
                }
                append(" define destinos y también argumentos tipados (data class).\n")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color(0xFF424242))
                        .padding(16.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("//En su version catalog\n \n") }
                            append(
                                "[versions]\n"+
                                "navigationCompose = \"2.9.4\"\n" +
                                        "\n" +
                                        "[libraries]\n" +
                                        "androidx-navigation-compose = { group = \"androidx.navigation\", name = \"navigation-compose\", version.ref = \"navigationCompose\" }\n"
                            )
                        },
                        style = TextStyle(fontSize = 16.sp, color = Color.White)
                    )
                    }
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color(0xFF424242))
                        .padding(16.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("//En su build.gradle(app)\n \n") }
                            append(
                                "dependencies {\n"+
                                        "implementation(libs.androidx.navigation.compose)\n"
                                      )
                        },
                        style = TextStyle(fontSize = 16.sp, color = Color.White)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        )
    }
}

@Composable
private fun CodigoNavHost() {
    Column {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "Ejemplo básico (como en este proyecto)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = "Primero creamos un Screens.kt para manejar nuestros nombres"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0xFF424242))
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("//Screens\n \n") }
                    append(
                        "import kotlinx.serialization.Serializable\n" +
                                "\n" +
                                "@Serializable\n" +
                                "object Home\n" +
                                "\n" +
                                "@Serializable\n" +
                                "object LeccionUno\n" +
                                "\n" +
                                "@Serializable\n" +
                                "data class LeccionTreceDetalle(val nombre: String, val edad: Int)"
                    )
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = "Segundo creamos un NavigatioWrapper.kt para manejar las rutas en este usaramos nuestros serializables"
        )
        Text(
            text = buildAnnotatedString {
                append(
                    "\nEn este punto dentro de nuestro composable NavigationWrapper declaramos nuestro navController:\n"
                )
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("val navController = rememberNavController()\n") }
            },
        )

        Text(
            text = buildAnnotatedString {
                append(
                    "\nProcedemos a declarar el NavHost pasando nuestro navcontroller para manejar nuestra navegación y nuestra ruta inicial:\n"
                )
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("NavHost(navController, startDestination = Home)\n") }
            },
        )

        Text(
            text = buildAnnotatedString {
                append(
                    "La declaración de cada ruta sería la siguiente:\n"
                )
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("composable<") }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) { append("Nombre Serializable") }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append(">{ \n") }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Blue)) { append("NuestraPantalla(") }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Magenta)) { append("parametro y nuestro navController") }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append(")\n") }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("}\n") }
            },
        )
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = "Quedando en conjunto esto:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0xFF424242))
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("//Nav Host y NavController\n \n") }
                    append(
                        "@Composable\n" +
                                "fun NavigationWrapper(){\n"+
                        "val navController = rememberNavController()\n" +
                                "NavHost(navController, startDestination = Home) {\n" +
                                "    composable<Home> { HomeScreen(\n" +
                                "       homeViewModel = HomeViewModel(),\n" +
                                "       navController = navController)}\n" +
                                "//Con parametros de navegación\n"+
                                "    composable<LeccionTreceDetalle> { backStackEntry ->\n" +
                                "            val detalle = backStackEntry.toRoute<LeccionTreceDetalle>()\n" +
                                "            LeccionTreceDetalleScreen(\n" +
                                "                navController = navController,\n" +
                                "                backStackArgs = detalle\n" +
                                "            )\n" +
                                "        }\n" +
                                "}\n"+
                        "}"
                    )
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
    }
}

@Composable
private fun EjemploPasoDeArgumentos(navController: NavController) {
    Column {
        Text(
            text = "Paso de argumentos serializables entre destinos"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Definimos un destino con argumentos: @Serializable data class LeccionTreceDetalle(val nombre: String, val edad: Int).\n"
        )
        Text(
            text = "Para navegar entre pantallas usamos nuestro navController:\n"
        )
        Text(
            text = buildAnnotatedString {
                append("Navegación sin parametros:\n")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("navController.navigate(Home)\n") }
            },
        )
        Text(
            text = buildAnnotatedString {
                append("Navegación con parametros:\n")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("navController.navigate(LeccionTreceDetalle(nombre = \"Lino\", edad = 25)\n") }
            },
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate(LeccionTreceDetalle(nombre = "Lino", edad = 25))
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Ir al detalle con argumentos (Lino, 25)")
        }
    }
}

@Composable
fun LeccionTreceDetalleScreen(navController: NavController, backStackArgs: Any? = null) {
    // Con Navigation Compose 2.8+, podemos obtener el objeto con toRoute en el composable de NavHost.
    // En este archivo solo mostramos el contenido. La obtención real se hace en NavigationWrapper.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val detalle = backStackArgs as? LeccionTreceDetalle
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = "Detalle recibido"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = "Nombre: " + (detalle?.nombre ?: "-")
        )
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = "Edad: " + (detalle?.edad?.toString() ?: "-")
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(LeccionTrece) }) {
            Text("Volver a Lección 13")
        }
    }
}
