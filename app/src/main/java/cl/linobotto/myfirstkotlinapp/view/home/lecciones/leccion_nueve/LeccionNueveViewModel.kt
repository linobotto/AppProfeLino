package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_nueve

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LeccionNueveViewModel : ViewModel(){

        val repository = DemoRepository()

        private val _uiState = MutableStateFlow(LeccionNueveUiState())
        val uiState: StateFlow<LeccionNueveUiState> = _uiState

        fun onNombreChange(value: String) {
            _uiState.update { it.copy(nombre = value) }
        }

        fun generarSaludo() {
            val saludo = repository.construirSaludo(_uiState.value.nombre)
            _uiState.update { it.copy(saludo = saludo) }
        }

        fun incrementar() {
            _uiState.update { it.copy(contador = it.contador + 1) }
        }

        fun reiniciar() {
            _uiState.value = LeccionNueveUiState()
        }
}

data class LeccionNueveUiState(
    val nombre: String = "",
    val saludo: String = "",
    val contador: Int = 0
)
