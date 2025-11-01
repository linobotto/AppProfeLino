package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_quince

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// UiState mínimo para la lección 15 (DataStore demo mantiene lógica en la UI)
data class LeccionQuinceUiState(
    val placeholder: Boolean = false
)

class LeccionQuinceViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LeccionQuinceUiState())
    val uiState: StateFlow<LeccionQuinceUiState> = _uiState
}