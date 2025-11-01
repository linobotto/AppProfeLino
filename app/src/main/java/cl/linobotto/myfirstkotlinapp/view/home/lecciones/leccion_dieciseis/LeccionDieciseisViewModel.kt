package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_dieciseis

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// UiState mínimo para la lección 16 (animaciones se demuestran en la UI)
data class LeccionDieciseisUiState(
    val placeholder: Boolean = false
)

class LeccionDieciseisViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LeccionDieciseisUiState())
    val uiState: StateFlow<LeccionDieciseisUiState> = _uiState
}