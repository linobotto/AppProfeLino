package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_once

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// UiState mínimo para respetar MVVM en la lección 11
data class LeccionOnceUiState(
    val placeholder: Boolean = false
)

class LeccionOnceViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LeccionOnceUiState())
    val uiState: StateFlow<LeccionOnceUiState> = _uiState
}