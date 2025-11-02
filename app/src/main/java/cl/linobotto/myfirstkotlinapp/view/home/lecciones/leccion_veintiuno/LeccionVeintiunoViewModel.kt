package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintiuno

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class LeccionVeintiunoUiState(
    val cargando: Boolean = false,
    val resultado: String = ""
)

class LeccionVeintiunoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LeccionVeintiunoUiState())
    val uiState: StateFlow<LeccionVeintiunoUiState> = _uiState.asStateFlow()

    fun simularTareaLarga() {
        _uiState.update { it.copy(cargando = true, resultado = "") }

        viewModelScope.launch {
            // La corrutina se inicia en el hilo Principal (Main) por defecto en viewModelScope

            // Simulamos una operación de red o base de datos en el hilo de IO
            val resultadoOperacion = withContext(Dispatchers.IO) {
                delay(2000) // Simula 2 segundos de espera
                "¡Tarea completada en el hilo IO!"
            }

            // Volvemos al hilo Principal para actualizar la UI
            _uiState.update { it.copy(cargando = false, resultado = resultadoOperacion) }
        }
    }
}