package cl.linobotto.myfirstkotlinapp.view.home.lecciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Composable
fun LeccionUnoScreen(navController: NavController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column() {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "Lección 1"
                )
                Image(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    painter = painterResource(id = R.drawable.kodee_winter),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(text = "Agregar librerias paso 1", style = TextStyle(fontSize = 16.sp))
                Spacer(modifier = Modifier.height(16.dp))
                LibreriasPaso1()
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Agregar librerias paso 2", style = TextStyle(fontSize = 16.sp))
                Spacer(modifier = Modifier.height(16.dp))
                LibreriasPaso2()
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate(Home) },
                ) {
                    Text(text = "Volver a las lecciones")
                }
            }
        }
    }
}

@Preview
@Composable
fun LibreriasPaso1(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .padding(16.dp)
    )
    {
        Text(
            text = buildAnnotatedString {
                append("Vaya a su catalogo de versiones \n ( ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red.copy(blue = 0.35f, green = 0.35f, red = 1f)
                    )
                ) {
                    append("libs.versions.toml")
                }
                append(" )\n")
            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
        Text(
            text = buildAnnotatedString {
                append("En la sección [libraries] se recomienda agregar las siguientes librerias: \n \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                    )
                ) {
                    append("#dependencia para implementar el viewmodel\n" +
                            "androidx-lifecycle-viewmodel-ktx = { group = \"androidx.lifecycle\", name = \"lifecycle-viewmodel-ktx\", version.ref = \"lifecycleRuntimeKtx\" }\n" +
                            "#agregar las dependencias de navegación\n" +
                            "androidx-navigation-compose = { group = \"androidx.navigation\", name = \"navigation-compose\", version.ref = \"navigationCompose\" }\n" +
                            "#agregar dependencia de serialización\n" +
                            "kotlin-serialization = { group = \"org.jetbrains.kotlinx\", name = \"kotlinx-serialization-json\", version.ref = \"kotlinSerialization\" }")
                }

            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}

@Preview
@Composable
fun LibreriasPaso2(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .padding(16.dp)
    )
    {
        Text(
            text = buildAnnotatedString {
                append("Vaya a su build gradle \n ( ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red.copy(blue = 0.35f, green = 0.35f, red = 1f)
                    )
                ) {
                    append("build.gradle.kts (Module :app)")
                }
                append(" )\n")
            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
        Text(
            text = buildAnnotatedString {
                append("Implemente las librerias agregadas en dependencies: \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                    )
                ) {
                    append(
                        "\n" +
                                "//Implementación del viewmodel\n" +
                                "implementation(libs.androidx.lifecycle.viewmodel.ktx)\n" +
                                "\n" +
                                "//Serialization\n" +
                                "implementation(libs.kotlin.serialization)\n" +
                                "\n" +
                                "//Navigation\n" +
                                "implementation(libs.androidx.navigation.compose)\n")
                }

            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}