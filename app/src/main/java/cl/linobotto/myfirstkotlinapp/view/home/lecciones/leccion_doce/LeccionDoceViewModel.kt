package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_doce

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// UiState mínimo para la lección 12
data class LeccionDoceUiState(
    val placeholder: Boolean = false
)

class LeccionDoceViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LeccionDoceUiState())
    val uiState: StateFlow<LeccionDoceUiState> = _uiState
}