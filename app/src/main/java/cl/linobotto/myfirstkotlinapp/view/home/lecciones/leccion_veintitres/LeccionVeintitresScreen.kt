package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintitres

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun LeccionVeintitresScreen(navController: NavController, viewModel: LeccionVeintitresViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

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
                text = "Lección 23: ProgressBar y Switch",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.kodee_progress),
                contentDescription = "La mascota de kotlin mostrando barras de progreso"
            )
            Spacer(Modifier.height(16.dp))

            SectionBox(title = "ProgressBar: Indicadores de Progreso") {
                Text("Se usan para mostrar que una operación está en curso.")
                Spacer(Modifier.height(8.dp))
                Text("Hay dos tipos:", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text("1. Indeterminado: Para cuando no se sabe cuánto tardará la tarea.")
                CircularProgressIndicator()
                Spacer(Modifier.height(8.dp))
                Text("2. Determinado: Para mostrar el progreso de una tarea con duración conocida. Ejemplo 50%")
                Spacer(Modifier.height(8.dp))
                LinearProgressIndicator(progress = { uiState.loadingProgress })
                Spacer(Modifier.height(8.dp))
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "Ejemplo: Carga con ProgressBar") {
                Button(onClick = { viewModel.startLoading() }, enabled = !uiState.isLoading) {
                    Text(if (uiState.isLoading) "Cargando..." else "Iniciar Carga")
                }
                if (uiState.isLoading) {
                    Spacer(Modifier.height(8.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        LinearProgressIndicator(
                            progress = { uiState.loadingProgress },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(text = "${(uiState.loadingProgress * 100).toInt()}% ")
                    }

                }
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "Switch: Selector de Encendido/Apagado") {
                Text("Un Switch permite al usuario alternar entre dos estados, típicamente 'encendido' y 'apagado'.")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Activar opción")
                    Switch(
                        checked = uiState.isSwitchOn,
                        onCheckedChange = { viewModel.onSwitchChanged(it) }
                    )
                }
                Text(text = "El switch está: ${if (uiState.isSwitchOn) "Encendido" else "Apagado"}")
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "Uso con ViewModel") {
                CajaCodigo(
                    codigo = """
                    // ViewModel
                    class LeccionVeintitresViewModel : ViewModel() {
                        private val _uiState = MutableStateFlow(LeccionVeintitresUiState())
                        val uiState = _uiState.asStateFlow()

                        fun onSwitchChanged(isOn: Boolean) {
                            _uiState.update { it.copy(isSwitchOn = isOn) }
                        }

                        fun startLoading() {
                            viewModelScope.launch {
                                _uiState.update { it.copy(isLoading = true, loadingProgress = 0f) }
                                for (i in 1..100) {
                                    delay(50)
                                    _uiState.update { it.copy(loadingProgress = i / 100f) }
                                }
                                _uiState.update { it.copy(isLoading = false) }
                            }
                        }
                    }

                    // Composable
                    @Composable
                    fun MyScreen(viewModel: LeccionVeintitresViewModel) {
                        val uiState by viewModel.uiState.collectAsState()

                        // ProgressBar
                        LinearProgressIndicator(progress = { uiState.loadingProgress })

                        // Switch
                        Switch(
                            checked = uiState.isSwitchOn,
                            onCheckedChange = { viewModel.onSwitchChanged(it) }
                        )
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