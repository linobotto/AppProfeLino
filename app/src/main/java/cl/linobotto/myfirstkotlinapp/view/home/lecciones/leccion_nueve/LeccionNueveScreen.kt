package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_nueve

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Preview(showBackground = true)
@Composable
private fun LeccionNuevePreview() {
    LeccionNueveScreen(navController = rememberNavController())
}

@Composable
fun LeccionNueveScreen(
    navController: NavController,
    leccionNueveViewModel: LeccionNueveViewModel = viewModel ()
) {
    val uiState by leccionNueveViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Lección 9: MVVM en Android con Compose"
            )
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.kodee_sharing),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))

            IntroMvvm()
            Spacer(modifier = Modifier.height(16.dp))

            ComoFunciona()
            Spacer(modifier = Modifier.height(16.dp))

            DemoMvvmVisual(uiState, leccionNueveViewModel)
            Spacer(modifier = Modifier.height(16.dp))

            CodigoMvvmBasico()
            Spacer(modifier = Modifier.height(16.dp))

            Ventajas()

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(modifier = Modifier.fillMaxWidth(), onClick = { navController.navigate(Home) }) {
                    Text(text = "Volver al inicio")
                }
            }
        }
    }
}

@Composable
private fun IntroMvvm() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF303030))
            .padding(16.dp)
    ) {
        Text(
            text = "¿Qué es MVVM?",
            style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "MVVM (Model–View–ViewModel) es un patrón de arquitectura que separa la UI (View) de la lógica de presentación (ViewModel) y los datos (Model). En Compose trabajamos observando estados expuestos por el ViewModel.",
            style = TextStyle(color = Color.White)
        )
    }
}

@Composable
private fun ComoFunciona() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF303030))
            .padding(16.dp)
    ) {
        Text(
            text = "Cómo funciona",
            style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "• View: Composables que solo dibujan el estado y envían eventos.\n" +
                    "• ViewModel: Mantiene el estado de la pantalla como StateFlow/LiveData y expone funciones para procesar eventos.\n" +
                    "• Model: Fuente de datos (repositorio, red, base local).\n\n" +
                    "Flujo de datos: Usuario → Evento en la View → ViewModel procesa → Actualiza el StateFlow → La View se recompone.",
            style = TextStyle(color = Color.White)
        )
    }
}


@Composable
private fun DemoMvvmVisual(
    uiState: LeccionNueveUiState,
    leccionNueveViewModel: LeccionNueveViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF303030))
            .padding(16.dp)
    ) {
        Text(
            text = "Demo MVVM (View ↔ ViewModel)",
            style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = uiState.nombre,
                onValueChange = { leccionNueveViewModel.onNombreChange(it) },
                modifier = Modifier.weight(1f),
                label = { Text("Tu nombre") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { leccionNueveViewModel.generarSaludo() }) {
                Text("Saludar")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = uiState.saludo, style = TextStyle(color = Color.White))

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { leccionNueveViewModel.incrementar() }) { Text("+1") }
            Button(onClick = { leccionNueveViewModel.reiniciar() }) { Text("Reiniciar") }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Contador: ${uiState.contador}", style = TextStyle(color = Color.White))
    }
}

@Composable
private fun CodigoMvvmBasico() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append("// ViewModel con StateFlow\n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF90CAF9)
                    )
                ) {
                    append("class LeccionNueveViewModel : ViewModel() {\n")
                }
                append("    val repository = DemoRepository()\n\n")
                append("    private val _uiState = MutableStateFlow(LeccionNueveUiState())\n")
                append("    val uiState: StateFlow<LeccionNueveUiState> = _uiState\n\n")
                append("    fun onNombreChange(value: String) {\n")
                append("        _uiState.update { it.copy(nombre = value) }\n")
                append("    }\n\n")
                append("    fun generarSaludo() {\n")
                append("        val saludo = repository.construirSaludo(_uiState.value.nombre)\n")
                append("        _uiState.update { it.copy(saludo = saludo) }\n")
                append("    }\n\n")
                append("    fun incrementar() {\n")
                append("        _uiState.update { it.copy(contador = it.contador + 1) }\n")
                append("    }\n\n")
                append("    fun reiniciar() {\n")
                append("        _uiState.value = LeccionNueveUiState()\n")
                append("    }\n")
                append("}\n")
            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
        Text(
            text = buildAnnotatedString {
                append("// La clase de datos (State)\n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF90CAF9)
                    )
                ) {
                    append("data class LeccionNueveUiState {\n")
                }
                append("    val nombre: String = \"\",\n")
                append("    val saludo: String = \"\",\n")
                append("    val contador: Int = 0\n\n")
                append("}\n")
            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
        Text(
            text = buildAnnotatedString {
                append("// Repositorio de prueba\n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF90CAF9)
                    )
                ) {
                    append("class DemoRepository {\n")
                }
                append("    fun construirSaludo(nombre: String): String = if (nombre.isBlank()) \"Hola 👋\" else \"Hola, \$nombre 👋\"\n")

                append("}\n")
            },
            style = TextStyle(fontSize = 16.sp, color = Color.White)
        )
    }
}

@Composable
private fun Ventajas() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF303030))
            .padding(16.dp)
    ) {
        Text(
            text = "Ventajas de MVVM",
            style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "• Separación clara de responsabilidades (UI vs lógica).\n" +
                    "• Facilita pruebas unitarias en el ViewModel.\n" +
                    "• Reutilización de lógica entre pantallas.\n" +
                    "• Estado observable y predecible (StateFlow/LiveData).\n" +
                    "• Compose se recompone automáticamente según el estado.\n" +
                    "• Favorece escalabilidad con capas (Repository, UseCases).",
            style = TextStyle(color = Color.White)
        )
    }
}
