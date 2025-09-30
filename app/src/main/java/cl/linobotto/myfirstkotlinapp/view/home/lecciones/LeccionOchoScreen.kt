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
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import cl.linobotto.myfirstkotlinapp.ui.theme.outlineDark

@Composable
fun LeccionOchoScreen(navController: NavController) {
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
                    text = "Lección 8"
                )
                Image(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    painter = painterResource(id = R.drawable.kodee_waving),
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
                IntroTexto()
                EjemplosTextVisual()
                BloqueCodigoTextBasico()
                EstilosTextoVisual()
                BloqueCodigoEstilosTexto()
                EjemplosTextFieldVisual()
                BloqueCodigoTextFields()
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
private fun IntroTexto() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF424242))
            .padding(16.dp)
    ) {
        Text(
            text = "Text y TextField",
            style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Text muestra contenido estático o dinámico. TextField/OutlinedTextField capturan entrada del usuario. Aquí veremos usos comunes y estilos.",
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EjemplosTextVisual() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF303030))
            .padding(16.dp)
    ) {
        Text(
            text = "Ejemplos visuales: Text",
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Texto simple", style = TextStyle(color = Color.White, fontSize = 16.sp))
        Text(
            text = "Negrita y tamaño grande",
            style = TextStyle(color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = "Cursiva centrada",
            style = TextStyle(
                color = Color.White,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Texto subrayado y espaciado",
            style = TextStyle(
                color = Color.White,
                textDecoration = TextDecoration.Underline,
                letterSpacing = 1.sp
            )
        )
        Text(
            text = "Máximo de una línea con puntos suspensivos al final si es muy largo...",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                append("Texto con ")
                withStyle(SpanStyle(color = Color(0xFFFFC107), fontWeight = FontWeight.Bold)) {
                    append("estilos")
                }
                append(" en partes del contenido usando AnnotatedString.")
            },
            style = TextStyle(color = Color.White)
        )
    }
}

@Preview
@Composable
private fun BloqueCodigoTextBasico() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color(0xFF90CAF9))) {
                    append("@Composable\n")
                    append("fun MisTextos() {\n")
                }
                append(" Text(\n" +
                        "            text = \"Ejemplos visuales: Text\",\n" +
                        "            style = TextStyle(\n" +
                        "                fontSize = 18.sp,\n" +
                        "                color = Color.White,\n" +
                        "                fontWeight = FontWeight.SemiBold\n" +
                        "            )\n" +
                        "        )\n" +
                        "        Spacer(modifier = Modifier.height(8.dp))\n" +
                        "        Text(text = \"Texto simple\", style = TextStyle(color = Color.White, fontSize = 16.sp))\n" +
                        "        Text(\n" +
                        "            text = \"Negrita y tamaño grande\",\n" +
                        "            style = TextStyle(color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)\n" +
                        "        )\n" +
                        "        Text(\n" +
                        "            text = \"Cursiva centrada\",\n" +
                        "            style = TextStyle(\n" +
                        "                color = Color.White,\n" +
                        "                fontStyle = FontStyle.Italic,\n" +
                        "                textAlign = TextAlign.Center\n" +
                        "            ),\n" +
                        "            modifier = Modifier.fillMaxWidth()\n" +
                        "        )\n" +
                        "        Text(\n" +
                        "            text = \"Texto subrayado y espaciado\",\n" +
                        "            style = TextStyle(\n" +
                        "                color = Color.White,\n" +
                        "                textDecoration = TextDecoration.Underline,\n" +
                        "                letterSpacing = 1.sp\n" +
                        "            )\n" +
                        "        )\n" +
                        "        Text(\n" +
                        "            text = \"Máximo de una línea con puntos suspensivos al final si es muy largo...\",\n" +
                        "            maxLines = 1,\n" +
                        "            overflow = TextOverflow.Ellipsis,\n" +
                        "            style = TextStyle(color = Color.White)\n" +
                        "        )\n" +
                        "        Spacer(modifier = Modifier.height(8.dp))\n" +
                        "        Text(\n" +
                        "            text = buildAnnotatedString {\n" +
                        "                append(\"Texto con \")\n" +
                        "                withStyle(SpanStyle(color = Color(0xFFFFC107), fontWeight = FontWeight.Bold)) {\n" +
                        "                    append(\"estilos\")\n" +
                        "                }\n" +
                        "                append(\" en partes del contenido usando AnnotatedString.\")\n" +
                        "            },\n" +
                        "            style = TextStyle(color = Color.White)\n" +
                        "        )\n")

                append("}\n")
            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EstilosTextoVisual() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF303030))
            .padding(16.dp)
    ) {
        Text(
            text = "Estilos útiles de Text",
            style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "lineHeight aumenta la altura de línea en textos largos.", style = TextStyle(color = Color.White))
        Text(
            text = "Este es un párrafo de ejemplo con más altura de línea para mejorar la lectura en bloques de texto más grandes.",
            style = TextStyle(color = Color.White, lineHeight = 24.sp)
        )
    }
}

@Preview
@Composable
private fun BloqueCodigoEstilosTexto() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append("// Algunos estilos de Text\n")
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color(0xFF90CAF9))) {
                    append("@Composable\n")
                    append("fun Estilos() {\n")
                }
                append("    Text(\"Cursiva\", style = TextStyle(fontStyle = FontStyle.Italic))\n")
                append("    Text(\"Subrayado\", style = TextStyle(textDecoration = TextDecoration.Underline))\n")
                append("    Text(\"Espaciado entre letras\", style = TextStyle(letterSpacing = 1.sp))\n")
                append("    Text(\"Altura de línea\", style = TextStyle(lineHeight = 24.sp))\n")
                append("}\n")
            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EjemplosTextFieldVisual() {
    var nombre by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var mostrarPass by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF303030))
            .padding(16.dp)
    ) {
        Text(
            text = "Ejemplos visuales: TextField y OutlinedTextField",
            style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Color.White
            ),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            placeholder = { Text("Escribe tu nombre") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            placeholder = { Text("nombre@dominio.com") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            placeholder = { Text("Mín. 8 caracteres") },
            singleLine = true,
            visualTransformation = if (mostrarPass) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { mostrarPass = !mostrarPass }) {
                Text(if (mostrarPass) "Ocultar" else "Mostrar")
            }
            Button(onClick = { nombre = ""; correo = ""; password = "" }) {
                Text("Limpiar")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Nombre: ${nombre}\nCorreo: ${correo}",
            style = TextStyle(color = Color.White)
        )
    }
}

@Preview
@Composable
private fun BloqueCodigoTextFields() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append("// TextField y OutlinedTextField\n")
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color(0xFF90CAF9))) {
                    append("@Composable\n")
                    append("fun Formulario() {\n")
                }
                append("    var nombre by rememberSaveable { mutableStateOf(\"\") }\n")
                append("    var correo by rememberSaveable { mutableStateOf(\"\") }\n")
                append("    var password by rememberSaveable { mutableStateOf(\"\") }\n\n")
                append("    OutlinedTextField(\n")
                append("        value = nombre,\n")
                append("        onValueChange = { nombre = it },\n")
                append("        label = { Text(\"Nombre\") },\n")
                append("        placeholder = { Text(\"Escribe tu nombre\") },\n")
                append("        singleLine = true\n")
                append("    )\n\n")
                append("    TextField(\n")
                append("        value = correo,\n")
                append("        onValueChange = { correo = it },\n")
                append("        label = { Text(\"Correo\") },\n")
                append("        placeholder = { Text(\"nombre@dominio.com\") },\n")
                append("        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)\n")
                append("    )\n\n")
                append("    OutlinedTextField(\n")
                append("        value = password,\n")
                append("        onValueChange = { password = it },\n")
                append("        label = { Text(\"Contraseña\") },\n")
                append("        singleLine = true,\n")
                append("        visualTransformation = PasswordVisualTransformation()\n")
                append("    )\n")
                append("}\n")
            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}