package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_diez

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// UiState mínimo para mantener el patrón MVVM (extensible a futuro)
data class LeccionDiezUiState(
    val placeholder: Boolean = false
)

class LeccionDiezViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LeccionDiezUiState())
    val uiState: StateFlow<LeccionDiezUiState> = _uiState
}