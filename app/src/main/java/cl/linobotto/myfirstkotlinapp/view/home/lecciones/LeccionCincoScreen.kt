package cl.linobotto.myfirstkotlinapp.view.home.lecciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Preview(showBackground = true)
@Composable
fun LeccionCincoScreenPreview() {
    LeccionCincoScreen(navController = rememberNavController())
}

@Composable
fun LeccionCincoScreen(navController: NavController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "5. Composable Spacer"
            )
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.kodee_sitting),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "¿Qué es Spacer?",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = "Spacer es un Composable que nos permite agregar espacios vacíos en un layout. Es muy útil para separar elementos en Column y Row, o para empujar componentes usando weight.",
                style = TextStyle(fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Uso básico",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Column + Spacer\n") }
                        append(
                            "Column(\n" +
                                    "    modifier = Modifier.fillMaxWidth()\n" +
                                    ") {\n" +
                                    "    Text(\"Arriba\")\n" +
                                    "    Spacer(Modifier.height(16.dp))\n" +
                                    "    Text(\"Abajo\")\n" +
                                    "}\n"
                        )
                    },
                    style = TextStyle(fontSize = 16.sp, color = Color.White)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color(0xFFEFEFEF))
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF64B5F6)),
                    contentAlignment = Alignment.Center
                ) { Text("Arriba", color = Color.White) }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF81C784)),
                    contentAlignment = Alignment.Center
                ) { Text("Abajo", color = Color.White) }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Spacer con width en Row",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Row + Spacer\n") }
                        append(
                            "Row(\n" +
                                    "    modifier = Modifier.fillMaxWidth()\n" +
                                    ") {\n" +
                                    "    Box(Modifier.width(60.dp).height(40.dp).background(Color.Red))\n" +
                                    "    Spacer(Modifier.width(12.dp))\n" +
                                    "    Box(Modifier.width(60.dp).height(40.dp).background(Color.Blue))\n" +
                                    "}\n"
                        )
                    },
                    style = TextStyle(fontSize = 16.sp, color = Color.White)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color(0xFFEFEFEF))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFE57373))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF64B5F6))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Usando weight para empujar/ocupar espacio",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Row con Spacer.weight(1f)\n") }
                        append(
                            "Row(Modifier.fillMaxWidth()) {\n" +
                                    "    Text(\"Izq\")\n" +
                                    "    Spacer(Modifier.weight(1f)) // ocupa todo el espacio disponible\n" +
                                    "    Text(\"Der\")\n" +
                                    "}\n"
                        )
                    },
                    style = TextStyle(fontSize = 16.sp, color = Color.White)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color(0xFFEFEFEF))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Izq")
                Spacer(modifier = Modifier.weight(1f))
                Text("Der")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Separadores iguales con pesos",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Column con Spacer de distintos tamaños\n") }
                        append(
                            "Column(Modifier.fillMaxWidth()) {\n" +
                                    "    Box(Modifier.height(40.dp).fillMaxWidth().background(Color(0xFFE57373)))\n" +
                                    "    Spacer(Modifier.height(8.dp))\n" +
                                    "    Box(Modifier.height(40.dp).fillMaxWidth().background(Color(0xFF81C784)))\n" +
                                    "    Spacer(Modifier.height(24.dp))\n" +
                                    "    Box(Modifier.height(40.dp).fillMaxWidth().background(Color(0xFF64B5F6)))\n" +
                                    "}\n"
                        )
                    },
                    style = TextStyle(fontSize = 16.sp, color = Color.White)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color(0xFFEFEFEF))
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFE57373))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF81C784))
                )
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF64B5F6))
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.navigate(Home) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Volver al inicio")
            }
        }
    }
}