package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_diecisiete

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// UiState mínimo para la lección 17 (YouTubePlayer se maneja en la UI)
data class LeccionDiecisieteUiState(
    val placeholder: Boolean = false
)

class LeccionDiecisieteViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LeccionDiecisieteUiState())
    val uiState: StateFlow<LeccionDiecisieteUiState> = _uiState
}