package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_dieciocho

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// UiState mínimo para la lección 18 (MediaPlayer se controla desde la UI)
data class LeccionDieciochoUiState(
    val placeholder: Boolean = false
)

class LeccionDieciochoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LeccionDieciochoUiState())
    val uiState: StateFlow<LeccionDieciochoUiState> = _uiState
}