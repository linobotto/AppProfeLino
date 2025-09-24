package cl.linobotto.myfirstkotlinapp.view.home.lecciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import cl.linobotto.myfirstkotlinapp.view.core.navigation.LeccionUno

@Composable
fun LeccionDosScreen(navController: NavController) {
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
                    text = "Lección 2"
                )
                Image(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    painter = painterResource(id = R.drawable.kodee_sitting),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(text = "Agregar plugin paso 1", style = TextStyle(fontSize = 16.sp))
                    Spacer(modifier = Modifier.height(16.dp))
                    PluginPaso1()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Agregar plugin paso 2", style = TextStyle(fontSize = 16.sp))
                    Spacer(modifier = Modifier.height(16.dp))
                    PluginPaso2()
                    Spacer(modifier = Modifier.height(16.dp))
                }
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
fun PluginPaso1(){
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
                append("En la sección [plugins] se recomienda agregar los siguientes plugin: \n \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                    )
                ) {
                    append("#agregar el plugin de serialización\n" +
                            "kotlin-serialization = { id = \"org.jetbrains.kotlin.plugin.serialization\", version.ref = \"kotlin\" }")
                }

            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}

@Preview
@Composable
fun PluginPaso2(){
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
                append("Aplique los plugin agregadas en plugins: \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                    )
                ) {
                    append(
                        "\n"+
                        "  //plugin Serialization\n" +
                                "    alias(libs.plugins.kotlin.serialization)")
                }

            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}