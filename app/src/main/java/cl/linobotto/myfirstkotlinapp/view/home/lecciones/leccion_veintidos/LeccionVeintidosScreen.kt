package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintidos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Composable
fun LeccionVeintidosScreen(
    navController: NavController,
    viewModel: LeccionVeintidosViewModel = viewModel()
) {

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
                text = "Lección 22: Ciclo de Vida de una Activity",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.life_cicle),
                contentDescription = "Dibujo de mascota de joven a adulta mayor"
            )
            Spacer(Modifier.height(8.dp))
            SectionBox(title = "Estados y Callbacks del Ciclo de Vida") {
                Text(
                    text = "Una Activity pasa por diferentes estados a lo largo de su existencia. El sistema nos notifica de estos cambios a través de métodos de callback que podemos sobrescribir.",
                    style = MaterialTheme.typography.bodyMedium,
                )

            }
            Spacer(Modifier.height(12.dp))

            var scale by remember { mutableStateOf(1f) }
            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .height(513.dp)
                    .background(Color.White)
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale = (scale * zoom).coerceIn(1f, 4f)
                            if (scale > 1f) {
                                offsetX += pan.x
                                offsetY += pan.y
                            } else {
                                offsetX = 0f
                                offsetY = 0f
                            }
                        }
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.activity_lifecycle),
                    contentDescription = "Diagrama del ciclo de vida de una Activity (pellizcar para hacer zoom)",
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offsetX,
                            translationY = offsetY
                        )
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "onCreate()") {
                Text(
                    text = "Se llama al crear la activity. Aquí configuras la UI.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Spacer(Modifier.height(8.dp))
            SectionBox(title = "onStart()") {
                Text(
                    text = "La activity está a punto de ser visible."
                )
            }
            Spacer(Modifier.height(8.dp))
            SectionBox(title = "onResume()") {
                Text(
                    text = "La activity es visible y tiene el foco del usuario."
                )
            }

            Spacer(Modifier.height(8.dp))
            SectionBox(title = "onPause()") {
                Text(
                    text = "La activity está a punto de pasar a segundo plano."
                )
            }

            Spacer(Modifier.height(8.dp))
            SectionBox(title = "onStop()") {
                Text(
                    text = "La activity ya no es visible para el usuario."
                )
            }

            Spacer(Modifier.height(8.dp))
            SectionBox(title = "onRestart()") {
                Text(
                    text = "Se llama después de onStop() cuando el usuario vuelve a la activity."
                )
            }

            Spacer(Modifier.height(8.dp))
            SectionBox(title = "onDestroy()") {
                Text(
                    text = "La activity está a punto de ser destruida."
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Componentes Conscientes del Ciclo de Vida") {
                Text(
                    text = "En lugar de sobrescribir todos estos métodos en tu Activity, la arquitectura moderna de Android recomienda usar Componentes Conscientes del Ciclo de Vida, como los ViewModels y LifecycleObservers.",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(8.dp))
                CajaCodigo(
                    codigo = """
                    class MiViewModel : ViewModel() {
                        init {
                            // El código aquí se ejecuta cuando el ViewModel se crea
                            // (sobrevive a cambios de configuración como rotaciones).
                        }

                        override fun onCleared() {
                            // Se llama cuando el ViewModel está a punto de ser destruido
                            // (cuando la Activity asociada es finalizada).
                            // ¡Lugar perfecto para limpiar recursos!
                            super.onCleared()
                        }
                    }
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(8.dp))
            SectionBox(title = "init") {
                Text("El código aquí se ejecuta cuando el ViewModel se crea (sobrevive a cambios de configuración como rotaciones).")
            }
            Spacer(Modifier.height(8.dp))
            SectionBox(title = "onCleared") {
                Text("Se llama cuando el ViewModel está a punto de ser destruido (cuando la Activity asociada es finalizada). ¡Lugar perfecto para limpiar")
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
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
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
