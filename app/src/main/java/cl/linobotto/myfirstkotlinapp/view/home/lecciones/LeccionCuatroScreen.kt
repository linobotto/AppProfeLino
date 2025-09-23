package cl.linobotto.myfirstkotlinapp.view.home.lecciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Composable
fun LeccionCuatroScreen(navController: NavController) {
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
                    text = "Lección 4"
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
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                RowYColumnExplicacion()
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

@Preview(showBackground = true, heightDp = 20000)
@Composable
fun RowYColumnExplicacion() {
    Column {
        Text(
            text = "Row y Column",
            style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Row y Column son composables que organizan hijos en fila u columna, respectivamente.",
            style = TextStyle(fontSize = 16.sp)
        )
        Text(
            text = "(Su equivalente en HTML serían contenedores con display:flex en dirección row o column)",
            style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.inversePrimary)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "¿Cómo declaro una Column?", style = TextStyle(fontSize = 16.sp))
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Column(")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Green.copy(blue = 0.5f, green = 1f, red = 1f)
                        )
                    ) {
                        append(" // Aquí van los modificadores y parámetros opcionales ")
                    }
                    append("){\n")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "    Text(\"Item 1\")\n" +
                                "    Text(\"Item 2\")\n" +
                                "    Text(\"Item 3\")\n"
                        )
                    }
                    append("}")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "¿Cómo declaro una Row?", style = TextStyle(fontSize = 16.sp))
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Row(")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Green.copy(blue = 0.5f, green = 1f, red = 1f)
                        )
                    ) {
                        append(" // Aquí van los modificadores y parámetros opcionales ")
                    }
                    append("){\n")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "    Text(\"A\")\n" +
                                "    Text(\"B\")\n" +
                                "    Text(\"C\")\n"
                        )
                    }
                    append("}")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Parámetros comunes útiles", style = TextStyle(fontSize = 16.sp))
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Modifier\n") }
                    append("    .fillMaxWidth() / .fillMaxHeight()\n")
                    append("    .padding(16.dp)\n\n")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("verticalArrangement (Column)\n") }
                    append("    Arrangement.Top, Center, Bottom, SpaceBetween, etc.\n\n")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("horizontalAlignment (Column)\n") }
                    append("    Alignment.Start, CenterHorizontally, End\n\n")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("horizontalArrangement (Row)\n") }
                    append("    Arrangement.Start, Center, End, SpaceBetween, etc.\n\n")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("verticalAlignment (Row)\n") }
                    append("    Alignment.Top, CenterVertically, Bottom\n")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }

        // Ejemplos visuales
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Ejemplos visuales", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary))

        // 1. Column básica
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "1) Column básica (apila elementos verticalmente)", style = TextStyle(fontSize = 16.sp))
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append(
                        "Column(\n" +
                                "    modifier = Modifier\n" +
                                "        .fillMaxWidth()\n" +
                                "        .clip(RoundedCornerShape(8.dp))\n" +
                                "        .background(color = Color(0xFFEFEFEF))\n" +
                                "        .padding(16.dp)\n" +
                                ") {\n"
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "    Box(Modifier.fillMaxWidth().height(40.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFFE57373))) { Text(\"Item 1\", color = Color.White) }\n" +
                                    "    Spacer(Modifier.height(8.dp))\n" +
                                    "    Box(Modifier.fillMaxWidth().height(40.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFF81C784))) { Text(\"Item 2\", color = Color.White) }\n" +
                                    "    Spacer(Modifier.height(8.dp))\n" +
                                    "    Box(Modifier.fillMaxWidth().height(40.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFF64B5F6))) { Text(\"Item 3\", color = Color.White) }\n"
                        )
                    }
                    append("}")
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
                    .background(Color(0xFFE57373)),
                contentAlignment = Alignment.Center
            ) { Text("Item 1", color = Color.White) }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF81C784)),
                contentAlignment = Alignment.Center
            ) { Text("Item 2", color = Color.White) }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF64B5F6)),
                contentAlignment = Alignment.Center
            ) { Text("Item 3", color = Color.White) }
        }

        // 2. Row básica
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "2) Row básica (distribuye elementos en horizontal)", style = TextStyle(fontSize = 16.sp))
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append(
                        "Row(\n" +
                                "    modifier = Modifier\n" +
                                "        .fillMaxWidth()\n" +
                                "        .clip(RoundedCornerShape(8.dp))\n" +
                                "        .background(color = Color(0xFFF5F5F5))\n" +
                                "        .padding(16.dp),\n" +
                                "    horizontalArrangement = Arrangement.spacedBy(8.dp)\n" +
                                ") {\n"
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "    Box(Modifier.width(60.dp).height(60.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFFBA68C8))) { Text(\"A\", color = Color.White) }\n" +
                                    "    Box(Modifier.width(60.dp).height(60.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFFFFB74D))) { Text(\"B\", color = Color.White) }\n" +
                                    "    Box(Modifier.width(60.dp).height(60.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFF4DB6AC))) { Text(\"C\", color = Color.White) }\n"
                        )
                    }
                    append("}")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0xFFF5F5F5))
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFBA68C8)),
                contentAlignment = Alignment.Center
            ) { Text("A", color = Color.White) }
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFFFB74D)),
                contentAlignment = Alignment.Center
            ) { Text("B", color = Color.White) }
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF4DB6AC)),
                contentAlignment = Alignment.Center
            ) { Text("C", color = Color.White) }
        }

        // 3. Row con Arrangement/Alignment
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "3) Row con Arrangement.SpaceBetween y CenterVertically", style = TextStyle(fontSize = 16.sp))
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append(
                        "Row(\n" +
                                "    modifier = Modifier\n" +
                                "        .fillMaxWidth()\n" +
                                "        .clip(RoundedCornerShape(8.dp))\n" +
                                "        .background(color = Color(0xFFE0E0E0))\n" +
                                "        .padding(16.dp),\n" +
                                "    horizontalArrangement = Arrangement.SpaceBetween,\n" +
                                "    verticalAlignment = Alignment.CenterVertically\n" +
                                ") {\n"
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "    Box(Modifier.width(40.dp).height(40.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFF9575CD)))\n" +
                                    "    Box(Modifier.width(40.dp).height(40.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFFFF8A65)))\n" +
                                    "    Box(Modifier.width(40.dp).height(40.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFF4FC3F7)))\n"
                        )
                    }
                    append("}")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0xFFE0E0E0))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF9575CD))
            )
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFFF8A65))
            )
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF4FC3F7))
            )
        }

        // 4. Uso de weight en Row
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "4) Row con weight (3 columnas iguales)", style = TextStyle(fontSize = 16.sp))
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append(
                        "Row(\n" +
                                "    modifier = Modifier\n" +
                                "        .fillMaxWidth()\n" +
                                "        .clip(RoundedCornerShape(8.dp))\n" +
                                "        .background(color = Color(0xFFF0F0F0))\n" +
                                "        .padding(8.dp)\n" +
                                ") {\n"
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "    Box(Modifier.weight(1f).height(40.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFF90CAF9))) { Text(\"1\", color = Color.White) }\n" +
                                    "    Spacer(Modifier.width(8.dp))\n" +
                                    "    Box(Modifier.weight(1f).height(40.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFFA5D6A7))) { Text(\"2\", color = Color.White) }\n" +
                                    "    Spacer(Modifier.width(8.dp))\n" +
                                    "    Box(Modifier.weight(1f).height(40.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFFFFCC80))) { Text(\"3\", color = Color.White) }\n"
                        )
                    }
                    append("}")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0xFFF0F0F0))
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF90CAF9)),
                contentAlignment = Alignment.Center
            ) { Text("1", color = Color.White) }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFA5D6A7)),
                contentAlignment = Alignment.Center
            ) { Text("2", color = Color.White) }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFFFCC80)),
                contentAlignment = Alignment.Center
            ) { Text("3", color = Color.White) }
        }

        // 5. Ejemplo práctico combinado
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "5) Fila de item: imagen + textos", style = TextStyle(fontSize = 16.sp))
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.Gray)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append(
                        "Row(\n" +
                                "    modifier = Modifier\n" +
                                "        .fillMaxWidth()\n" +
                                "        .clip(RoundedCornerShape(12.dp))\n" +
                                "        .background(color = Color(0xFFFAFAFA))\n" +
                                "        .padding(12.dp),\n" +
                                "    verticalAlignment = Alignment.CenterVertically,\n" +
                                "    horizontalArrangement = Arrangement.spacedBy(12.dp)\n" +
                                ") {\n"
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "    Image(painter = painterResource(id = R.drawable.kodee_sharing), contentDescription = null, modifier = Modifier.width(56.dp).height(56.dp))\n" +
                                    "    Column(Modifier.weight(1f)) {\n" +
                                    "        Text(\"Título\", style = MaterialTheme.typography.bodyLarge)\n" +
                                    "        Text(\"Subtítulo de ejemplo\", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.inversePrimary)\n" +
                                    "    }\n" +
                                    "    Text(\"Acción\", color = MaterialTheme.colorScheme.primary)\n"
                        )
                    }
                    append("}")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color(0xFFFAFAFA))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.kodee_sharing),
                contentDescription = null,
                modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Título", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Subtítulo de ejemplo", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.inversePrimary)
            }
            Text(text = "Acción", color = MaterialTheme.colorScheme.primary)
        }

        // 6. Column con alineación y distribución
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "6) Column con CenterHorizontally + SpaceEvenly", style = TextStyle(fontSize = 16.sp))
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append(
                        "Column(\n" +
                                "    modifier = Modifier\n" +
                                "        .fillMaxWidth()\n" +
                                "        .height(150.dp)\n" +
                                "        .clip(RoundedCornerShape(8.dp))\n" +
                                "        .background(color = Color(0xFFEDE7F6))\n" +
                                "        .padding(16.dp),\n" +
                                "    horizontalAlignment = Alignment.CenterHorizontally,\n" +
                                "    verticalArrangement = Arrangement.SpaceEvenly\n" +
                                ") {\n"
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "    Box(Modifier.width(36.dp).height(36.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFF7986CB)))\n" +
                                    "    Box(Modifier.width(36.dp).height(36.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFF64B5F6)))\n" +
                                    "    Box(Modifier.width(36.dp).height(36.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFF4DD0E1)))\n"
                        )
                    }
                    append("}")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0xFFEDE7F6))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF7986CB))
            )
            Box(
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF64B5F6))
            )
            Box(
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF4DD0E1))
            )
        }
    }
}
