package cl.linobotto.myfirstkotlinapp.view.home.lecciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavHostController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home


@Composable
fun LeccionTresScreen(navController: NavHostController) {
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
                    text = "Lección 3"
                )
                Image(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    painter = painterResource(id = R.drawable.kodee_in_love),
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
                TextoBox()
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
fun TextoBox() {
    Column {
        Text(text = "Box", style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Los Box son el composable pensado especialmente para contener otros composables",
            style = TextStyle(fontSize = 16.sp)
        )
        Text(
            text = "(Su equivalente en Html seria el div)",
            style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.inversePrimary)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "¿Como declaro un Box?", style = TextStyle(fontSize = 16.sp))
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
                    append("Box(")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Green.copy(blue = 0.5f, green = 1f, red = 1f)
                        )
                    ) {
                    append(" //En esta sección oran los modificadores ")
                    }
                    append("){")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "\n" +
                                    "   //Otros Composables ejemplo otro Box\n" +
                                    "   Box(){\n   }\n"
                        )
                    }
                    append("}")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Modificadores",
            style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = buildAnnotatedString {
                append("Los modificadores son usados para ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("personalizar la apariencia y el comportamiento de los elementos")
                }
                append(" de la interfaz de usuario")
            },
            style = TextStyle(fontSize = 16.sp, color = Color.Black)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = buildAnnotatedString {
                append("Algunos de los modificadores mas usados son: \n \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("padding: ")
                }
                append("Los modificadores padding se utilizan para agregar espacios entre elementos de la interfaz de usuario. \n \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("fillMaxWidth \n")
                }
                append("Los modificadores fillMaxWidth se utilizan para establecer el ancho máximo de un elemento de la interfaz de usuario. \n \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("background  \n")
                }
                append("Los modificadores background se utilizan para establecer el color de fondo de un elemento de la interfaz de usuario. \n \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("color \n")
                }
                append("Los modificadores color se utilizan para establecer el color de los elementos de la interfaz de usuario. \n \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("clip \n")
                }
                append("Los modificadores clip se utilizan para cortar el borde de un elemento de la interfaz de usuario. \n \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("shape \n")
                }
                append("Los modificadores shape se utilizan para establecer la forma de un elemento de la interfaz de usuario. \n \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("height \n")
                }
                append("Los modificadores height se utilizan para establecer la altura de un elemento de la interfaz de usuario. \n \n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("width \n")
                }
                append("Los modificadores width se utilizan para establecer el ancho de un elemento de la interfaz de usuario. \n \n")

            },
            style = TextStyle(fontSize = 16.sp, color = Color.Black)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Ejemplo:",
            style = TextStyle(fontSize = 16.sp)
        )
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
                    append("Box(")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Green.copy(blue = 0.5f, green = 1f, red = 1f)
                        )
                    ) {
                        append(" modifier = Modifier\n" +
                                "                .height(30.dp)\n" +
                                "                .width(30.dp)\n" +
                                "                .background(color = Color.Green)\n" +
                                "                .clip(RoundedCornerShape(8.dp)) ")
                    }
                    append("){")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "\n" +
                                    "   //Otros Composables ejemplo otro Box\n" +
                                    "   Box(){\n   }\n"
                        )
                    }
                    append("}")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "nos mostraría esto en la pantalla:",
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
                .background(color = Color.Green)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Alineaciones:",
            style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Dentro de Box podemos tener alineaciones, estas pueden ser horizontales o verticales", style = TextStyle(fontSize = 16.sp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Ejemplo de una alineación centrada:",
            style = TextStyle(fontSize = 16.sp)
        )
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
                    append("Box(")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Green.copy(blue = 0.5f, green = 1f, red = 1f)
                        )
                    ) {
                        append(
                            "\n" +
                                "            modifier = Modifier\n" +
                                "               .height(50.dp)\n" +
                                "               .fillMaxWidth()\n" +
                                "               .background(color = Color.Magenta)\n" +
                                "               .clip(RoundedCornerShape(8.dp))\n" +
                                "            ,\n" +
                                "            contentAlignment = Alignment.Center ")
                    }
                    append("){")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "\n" +
                                    "   Text(\"Mi objeto\", \n      style = TextStyle(\n" +
                                    "                    fontSize = 18.sp, \n" +
                                    "                    color = Color.White,\n " +
                                    "                    fontWeight = FontWeight.Bold))"
                        )
                    }
                    append("\n}")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "nos mostraría esto en la pantalla:",
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(color = Color.Magenta)
                .clip(RoundedCornerShape(8.dp))
            ,
            contentAlignment = Alignment.Center
        ){
            Text("Mi objeto", style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Ejemplo de una alineación centrada a la izquierda:",
            style = TextStyle(fontSize = 16.sp)
        )
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
                    append("Box(")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Green.copy(blue = 0.5f, green = 1f, red = 1f)
                        )
                    ) {
                        append(
                            "\n" +
                                    "            modifier = Modifier\n" +
                                    "               .height(50.dp)\n" +
                                    "               .fillMaxWidth()\n" +
                                    "               .background(color = Color.Magenta)\n" +
                                    "               .clip(RoundedCornerShape(8.dp))\n" +
                                    "            ,\n" +
                                    "            contentAlignment = Alignment.CenterStart ")
                    }
                    append("){")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue.copy(blue = 1f, green = 0.35f, red = 1f)
                        )
                    ) {
                        append(
                            "\n" +
                                    "   Text(\"Mi objeto\", \n      style = TextStyle(\n" +
                                    "                    fontSize = 18.sp, \n" +
                                    "                    color = Color.White,\n " +
                                    "                    fontWeight = FontWeight.Bold))"
                        )
                    }
                    append("\n}")
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "nos mostraría esto en la pantalla:",
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(color = Color.Magenta)
                .clip(RoundedCornerShape(8.dp))
            ,
            contentAlignment = Alignment.CenterStart
        ){
            Text("Mi objeto", style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Existen varios tipos de alineación al escribir \"Alignment\" y presionar el punto \".\" Android Studio nos mostrara las opciones disponibles",
            style = TextStyle(fontSize = 16.sp)
        )
    }
}