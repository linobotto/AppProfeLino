package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_trece

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// UiState mínimo para la lección 13
data class LeccionTreceUiState(
    val placeholder: Boolean = false
)

class LeccionTreceViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LeccionTreceUiState())
    val uiState: StateFlow<LeccionTreceUiState> = _uiState
}