package cl.linobotto.myfirstkotlinapp.view.home.lecciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Composable
fun LeccionSieteScreen(navController: NavController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "Lección 7"
                )
                Image(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    painter = painterResource(id = R.drawable.kodee_greeting),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IntroEstado()
                EjemploContadorVisual()
                CodigoContador()
                EjemploTextoVisual()
                CodigoTexto()
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

@Composable
private fun IntroEstado() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF424242))
            .padding(16.dp)
    ) {
        Text(
            text = "Estados y Recomposición",
            style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "En Jetpack Compose, el UI se dibuja a partir del estado. Cuando el estado cambia, la UI se recompone (se vuelve a dibujar). Para recordar valores entre recomposiciones usamos remember y, si queremos que sobrevivan a cambios de configuración (como rotación), usamos rememberSaveable.",
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}

@Composable
private fun EjemploContadorVisual() {
    var count by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF303030))
            .padding(16.dp)
    ) {
        Text(
            text = "Ejemplo visual: Contador",
            style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Valor actual: $count",
            style = TextStyle(fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { if (count > 0) count-- }) { Text(text = "-1") }
            Button(onClick = { count++ }) { Text(text = "+1") }
            Button(onClick = { count = 0 }) { Text(text = "Reset") }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Cada vez que cambias el valor, Compose detecta el cambio de estado y recompone solo las partes necesarias.",
            style = TextStyle(fontSize = 14.sp, color = Color.White)
        )
    }
}

@Preview
@Composable
private fun CodigoContador() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append("// Contador con estado recordado y guardable\n")
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color(0xFF90CAF9))) {
                    append("@Composable\n")
                    append("fun Contador() {\n")
                }
                append("    var count by rememberSaveable { mutableStateOf(0) }\n\n")
                append("    Row {\n")
                append("        Button(onClick = { if (count > 0) count-- }) { Text(\"-1\") }\n")
                append("        Button(onClick = { count++ }) { Text(\"+1\") }\n")
                append("        Text(text = \"Valor: \$count\")\n")
                append("    }\n")
                append("}")
            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}

@Composable
private fun EjemploTextoVisual() {
    var nombre by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF303030))
            .padding(16.dp)
    ) {
        Text(
            text = "Ejemplo visual: Input de texto",
            style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if (nombre.isBlank()) "Escribe tu nombre arriba" else "Hola, $nombre!",
            style = TextStyle(fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Medium)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "El texto mostrado se actualiza automáticamente con cada cambio en el estado del campo.",
            style = TextStyle(fontSize = 14.sp, color = Color.White)
        )
    }
}

@Preview
@Composable
private fun CodigoTexto() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append("// Entrada de texto con recomposición\n")
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color(0xFF90CAF9))) {
                    append("@Composable\n")
                    append("fun SaludoConNombre() {\n")
                }
                append("    var nombre by rememberSaveable { mutableStateOf(\"\") }\n\n")
                append("    Column {\n")
                append("        OutlinedTextField(\n")
                append("            value = nombre,\n")
                append("            onValueChange = { nombre = it },\n")
                append("            label = { Text(\"Nombre\") }\n")
                append("        )\n")
                append("        Text(text = if (nombre.isBlank()) \"Escribe tu nombre\" else \"Hola, \$nombre!\")\n")
                append("    }\n")
                append("}")
            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}
