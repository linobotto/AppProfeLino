package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintisiete

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Composable
fun LeccionVeintisieteScreen(
    navController: NavController,
    viewModel: LeccionVeintisieteViewModel = viewModel()
) {
    val showBasicDialog by viewModel.showBasicDialog.collectAsState()
    val showCustomDialog by viewModel.showCustomDialog.collectAsState()

    if (showBasicDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.onDialogDismiss() },
            title = { Text("Diálogo Básico") },
            text = { Text("Este es un ejemplo de un AlertDialog simple. Se utiliza para mostrar información importante.") },
            confirmButton = {
                TextButton(onClick = { viewModel.onDialogDismiss() }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.onDialogDismiss() }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (showCustomDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.onDialogDismiss() },
            title = { Text("Diálogo Personalizado") },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.kodee_modal),
                        contentDescription = "Happy Kodee",
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Puedes añadir cualquier Composable, como texto o imágenes, para personalizar el contenido.")
                }
            },
            confirmButton = {
                TextButton(onClick = { viewModel.onDialogDismiss() }) {
                    Text("¡Genial!")
                }
            }
        )
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Lección 27: AlertDialog",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.kodee_alert),
                    modifier = Modifier.height(120.dp),
                    contentDescription = "QR Code",
                )
            }

            SectionBox(title = "1. ¿Qué es un AlertDialog?") {
                Text(
                    """Un AlertDialog es un componente que interrumpe el flujo del usuario para mostrar información crítica o solicitar una decisión. Debe usarse con moderación, ya que puede ser disruptivo."""
                )
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "2. Ejemplo de AlertDialog Básico") {
                Text("Este es el diálogo más común. Incluye un título, un mensaje y botones de acción.")
                Spacer(Modifier.height(8.dp))
                Button(onClick = { viewModel.onBasicDialogClicked() }) {
                    Text("Mostrar Diálogo Básico")
                }
                CajaCodigo(
                    codigo = """
                        var showDialog by remember { mutableStateOf(false) }

                        if (showDialog) {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                title = { Text("Título") },
                                text = { Text("Mensaje del diálogo.") },
                                confirmButton = {
                                    TextButton(onClick = { showDialog = false }) {
                                        Text("Aceptar")
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = { showDialog = false }) {
                                        Text("Cancelar")
                                    }
                                }
                            )
                        }
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "3. Ejemplo de AlertDialog Personalizado") {
                Text("Puedes personalizar el contenido del diálogo para incluir imágenes, campos de texto o cualquier otro Composable.")
                Spacer(Modifier.height(8.dp))
                Button(onClick = { viewModel.onCustomDialogClicked() }) {
                    Text("Mostrar Diálogo Personalizado")
                }
                CajaCodigo(
                    codigo = """
                        AlertDialog(
                            onDismissRequest = { /*...*/ },
                            title = { Text("Diálogo con Imagen") },
                            text = {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Image(
                                        painter = painterResource(id = R.drawable.kodee_happy),
                                        contentDescription = null,
                                        modifier = Modifier.size(80.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Contenido personalizado.")
                                }
                            },
                            confirmButton = { /*...*/ }
                        )
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(24.dp))

            Button(onClick = { navController.navigate(Home) }, modifier = Modifier.fillMaxWidth()) {
                Text("Volver a las lecciones")
            }
        }
    }
}

@Composable
private fun SectionBox(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(12.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        content()
    }
}

@Composable
private fun CajaCodigo(codigo: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1E1E1E))
            .padding(12.dp)
    ) {
        Text(
            text = codigo,
            style = TextStyle(
                color = Color(0xFFD4D4D4),
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        )
    }
}