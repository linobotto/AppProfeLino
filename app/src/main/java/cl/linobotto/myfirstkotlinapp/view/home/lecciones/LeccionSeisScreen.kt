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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Preview(showBackground = true)
@Composable
fun LeccionSeisScreenPreview() {
    LeccionSeisScreen(navController = rememberNavController())
}

@Composable
fun LeccionSeisScreen(navController: NavController) {
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
                text = "6. Composable ConstraintLayout"
            )
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.kodee_waving),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "¿Qué es ConstraintLayout en Compose?",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = "ConstraintLayout es un layout potente que permite posicionar elementos en relación a otros elementos o a su contenedor usando restricciones (constraints). Es ideal para interfaces complejas manteniendo un solo contenedor.",
                style = TextStyle(fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Ejemplo 1: Posicionamiento básico",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )

            // Vista previa visual del ejemplo 1
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE3F2FD))
                    .padding(16.dp)
            ) {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (title, subtitle, boton) = createRefs()

                    Text(
                        text = "Título",
                        modifier = Modifier.constrainAs(title) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        },
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Subtítulo bajo el título",
                        modifier = Modifier.constrainAs(subtitle) {
                            top.linkTo(title.bottom, margin = 8.dp)
                            start.linkTo(title.start)
                        }
                    )
                    Button(
                        onClick = {},
                        modifier = Modifier.constrainAs(boton) {
                            top.linkTo(subtitle.bottom, margin = 12.dp)
                            end.linkTo(parent.end)
                        }
                    ) { Text("Acción") }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            CodeBlock(
                title = "Código (Ejemplo 1)",
                code = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("ConstraintLayout básico\n") }
                    append(
                        "ConstraintLayout {\n" +
                            "    val (title, subtitle, boton) = createRefs()\n" +
                            "    Text(\"Título\", Modifier.constrainAs(title) {\n" +
                            "        top.linkTo(parent.top)\n" +
                            "        start.linkTo(parent.start)\n" +
                            "    })\n" +
                            "    Text(\"Subtítulo\", Modifier.constrainAs(subtitle) {\n" +
                            "        top.linkTo(title.bottom, 8.dp)\n" +
                            "        start.linkTo(title.start)\n" +
                            "    })\n" +
                            "    Button(Modifier.constrainAs(boton) {\n" +
                            "        top.linkTo(subtitle.bottom, 12.dp)\n" +
                            "        end.linkTo(parent.end)\n" +
                            "    }) { Text(\"Acción\") }\n" +
                            "}\n"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Ejemplo 2: Guidelines y dimensiones",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )

            // Vista previa visual del ejemplo 2
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF1F8E9))
                    .padding(16.dp)
            ) {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val startGuideline = createGuidelineFromStart(0.1f)
                    val endGuideline = createGuidelineFromEnd(0.1f)
                    val (caja, texto) = createRefs()

                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .background(Color(0xFF80DEEA))
                            .constrainAs(caja) {
                                start.linkTo(startGuideline)
                                end.linkTo(endGuideline)
                                width = Dimension.fillToConstraints
                            }
                    )

                    Text(
                        text = "La caja se estira entre guidelines (10% a cada lado)",
                        modifier = Modifier.constrainAs(texto) {
                            top.linkTo(caja.bottom, margin = 12.dp)
                            start.linkTo(caja.start)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            CodeBlock(
                title = "Código (Ejemplo 2)",
                code = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("Guidelines + Dimension.fillToConstraints\n") }
                    append(
                        "ConstraintLayout {\n" +
                            "    val startGuideline = createGuidelineFromStart(0.1f)\n" +
                            "    val endGuideline = createGuidelineFromEnd(0.1f)\n" +
                            "    val (caja, texto) = createRefs()\n\n" +
                            "    Box(Modifier.constrainAs(caja) {\n" +
                            "        start.linkTo(startGuideline)\n" +
                            "        end.linkTo(endGuideline)\n" +
                            "        width = Dimension.fillToConstraints\n" +
                            "    }.height(60.dp))\n\n" +
                            "    Text(\"Se estira entre guidelines\", Modifier.constrainAs(texto) {\n" +
                            "        top.linkTo(caja.bottom, 12.dp)\n" +
                            "        start.linkTo(caja.start)\n" +
                            "    })\n" +
                            "}\n"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Consejos",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Bullet(text = "Usa createRefs() para referenciar tus composables.")
                Bullet(text = "Dimension.prefWidth/Height, wrapContent, fillToConstraints te ayudan a controlar tamaño.")
                Bullet(text = "Guidelines y Barriers son excelentes para layouts responsivos.")
            }

            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(modifier = Modifier.fillMaxWidth(), onClick = { navController.navigate(Home) }) {
                    Text("Volver al Home")
                }
            }
        }
    }
}

@Composable
private fun CodeBlock(title: String, code: CharSequence) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0xFF424242))
                .padding(16.dp)
        ) {
            Text(text = code.toString(), style = TextStyle(fontSize = 14.sp, color = Color.White))
        }
    }
}

@Composable
private fun Bullet(text: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        Text("• ", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
        Text(text, style = TextStyle(fontSize = 16.sp))
    }
}
