package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintitres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LeccionVeintitresUiState(
    val loadingProgress: Float = 0.5f,
    val isLoading: Boolean = false,
    val isSwitchOn: Boolean = false
)

class LeccionVeintitresViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LeccionVeintitresUiState())
    val uiState = _uiState.asStateFlow()

    fun onSwitchChanged(isOn: Boolean) {
        _uiState.update { it.copy(isSwitchOn = isOn) }
    }

    fun startLoading() {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, loadingProgress = 0f) }
            for (i in 1..100) {
                delay(50) // Simula una carga de 5 segundos en total
                _uiState.update { it.copy(loadingProgress = i / 100f) }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}