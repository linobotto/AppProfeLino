package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintiuno

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
fun LeccionVeintiunoScreen(navController: NavController, viewModel: LeccionVeintiunoViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

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
                text = "Lección 21: Corrutinas, Scopes y Dispatchers",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))
            Image(
                modifier = Modifier
                    .width(200.dp).height(200.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.kodee_async),
                contentDescription = null
            )
            Spacer(Modifier.height(12.dp))

            SectionBox(title = "¿Qué son las Corrutinas?") {
                Text(
                    text = "Son un patrón de diseño para simplificar código que se ejecuta de forma asíncrona. Permiten escribir código no bloqueante de manera secuencial y legible, evitando el ‘callback hell’.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Dispatchers: ¿En qué hilo se ejecuta el código?") {
                CajaCodigo(
                    codigo = """
                    // Dispatchers.Main
                    // - Usado para operaciones en el hilo principal de Android (UI).
                    // - Actualizar vistas, mostrar Toasts, etc.
                    
                    // Dispatchers.IO
                    // - Optimizado para operaciones de entrada/salida (I/O).
                    // - Llamadas a APIs, acceso a base de datos, leer/escribir archivos.
                    
                    // Dispatchers.Default
                    // - Para tareas que consumen mucha CPU (CPU-intensive).
                    // - Ordenar listas grandes, procesar imágenes, cálculos complejos.
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "CoroutineScope: ¿Dónde viven las corrutinas?") {
                Text(
                    text = "Un CoroutineScope gestiona el ciclo de vida de las corrutinas. Cuando se cancela un scope, todas las corrutinas que se lanzaron dentro de él también se cancelan. Esto es clave para evitar memory leaks.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(8.dp))
                CajaCodigo(
                    codigo = """
                    // viewModelScope
                    // - Un scope predefinido en los ViewModels de AndroidX.
                    // - Se vincula al ciclo de vida del ViewModel: se cancela automáticamente
                    //   cuando el ViewModel es destruido (ej: al cerrar la pantalla).
                    """.trimIndent()
                )
            }
            
            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Ejemplo práctico en ViewModel") {
                Text(
                    text = "Simularemos una tarea larga (como una llamada a API) que se ejecuta en un hilo de fondo (IO) y luego actualiza la UI en el hilo principal (Main).",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    enabled = !uiState.cargando,
                    onClick = { viewModel.simularTareaLarga() }
                ) { Text(if (uiState.cargando) "Simulando..." else "Iniciar Tarea Larga") }

                if (uiState.resultado.isNotBlank()) {
                    Spacer(Modifier.height(8.dp))
                    Text(text = uiState.resultado, style = MaterialTheme.typography.bodyLarge)
                }

                Spacer(Modifier.height(8.dp))
                CajaCodigo(
                    codigo = """
                    class LeccionVeintiunoViewModel : ViewModel() {
                        fun simularTareaLarga() {
                            viewModelScope.launch { // Inicia en Dispatchers.Main
                                // Cambiamos al hilo IO para la tarea pesada
                                val resultado = withContext(Dispatchers.IO) {
                                    delay(2000) // Simula espera
                                    "¡Tarea completada!"
                                }
                                // withContext nos devuelve al hilo anterior (Main)
                                // para actualizar la UI de forma segura.
                                _uiState.update { it.copy(resultado = resultado) }
                            }
                        }
                    }
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