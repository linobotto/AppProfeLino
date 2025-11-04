package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veinticuatro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
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
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeccionVeinticuatroScreen(navController: NavController, viewModel: LeccionVeinticuatroViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val df = DecimalFormat("#.##")

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
                text = "Lección 24: Sliders",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))
            Image(
                modifier = Modifier.height(100.dp),
                painter = painterResource(id = R.drawable.kodee_slide),
                contentDescription = "Imagen de personaje sosteniendo un slider",
            )
            Spacer(Modifier.height(16.dp))

            SectionBox(title = "Slider (Continuo)") {
                Text("Permite al usuario seleccionar un valor de un rango continuo.")
                Slider(
                    value = uiState.sliderValue,
                    onValueChange = { viewModel.onSliderChanged(it) }
                )
                Text("Valor: ${df.format(uiState.sliderValue)}")
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "Slider con Pasos (Stepped Slider)") {
                Text("Permite al usuario seleccionar un valor de un rango, pero forzando la selección a puntos específicos (pasos).")
                Slider(
                    value = uiState.steppedSliderValue,
                    onValueChange = { viewModel.onSteppedSliderChanged(it) },
                    valueRange = 0f..100f,
                    steps = 4 // (100 - 0) / 5 = 20. 4 steps son 5 secciones.
                )
                Text("Valor: ${uiState.steppedSliderValue.toInt()}")
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "RangeSlider (Rango)") {
                Text("Permite al usuario seleccionar un rango de valores (un valor inicial y uno final).")
                RangeSlider(
                    value = uiState.rangeSliderValue,
                    onValueChange = { viewModel.onRangeSliderChanged(it) },
                    valueRange = 0f..100f,
                    steps = 9
                )
                Text("Rango seleccionado: ${uiState.rangeSliderValue.start.toInt()} - ${uiState.rangeSliderValue.endInclusive.toInt()}")
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "Código de Ejemplo con ViewModel") {
                CajaCodigo(
                    codigo = """
                    // ViewModel
                    class LeccionVeinticuatroViewModel : ViewModel() {
                        // ... (definición de _uiState y uiState)

                        fun onSliderChanged(newValue: Float) { /* ... */ }
                        fun onSteppedSliderChanged(newValue: Float) { /* ... */ }
                        fun onRangeSliderChanged(newRange: ClosedFloatingPointRange<Float>) { /* ... */ }
                    }

                    // Composable
                    @Composable
                    fun MyScreen(viewModel: LeccionVeinticuatroViewModel) {
                        val uiState by viewModel.uiState.collectAsState()

                        // Slider continuo
                        Slider(
                            value = uiState.sliderValue,
                            onValueChange = { viewModel.onSliderChanged(it) }
                        )

                        // Stepped Slider
                        Slider(
                            value = uiState.steppedSliderValue,
                            onValueChange = { viewModel.onSteppedSliderChanged(it) },
                            valueRange = 0f..100f,
                            steps = 4
                        )

                        // Range Slider
                        RangeSlider(
                            value = uiState.rangeSliderValue,
                            onValueChange = { viewModel.onRangeSliderChanged(it) },
                            valueRange = 0f..100f
                        )
                    }
                    """
                        .trimIndent()
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