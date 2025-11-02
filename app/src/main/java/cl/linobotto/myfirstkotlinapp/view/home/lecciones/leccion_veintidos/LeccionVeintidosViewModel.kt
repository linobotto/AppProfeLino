package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintidos

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// No complex state is needed for this lesson, but we maintain the structure.
data class LeccionVeintidosUiState(
    val log: List<String> = emptyList()
)

class LeccionVeintidosViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LeccionVeintidosUiState())
    val uiState = _uiState.asStateFlow()

    // In a real app, we might log lifecycle events here using a LifecycleObserver.
    // For this lesson, the code examples will be the primary focus.
}