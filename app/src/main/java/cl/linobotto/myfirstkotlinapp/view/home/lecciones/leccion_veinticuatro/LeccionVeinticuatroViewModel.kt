package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veinticuatro

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalMaterial3Api::class)
data class LeccionVeinticuatroUiState(
    val sliderValue: Float = 0.5f,
    val steppedSliderValue: Float = 20f,
    val rangeSliderValue: ClosedFloatingPointRange<Float> = 25f..75f
)

@OptIn(ExperimentalMaterial3Api::class)
class LeccionVeinticuatroViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LeccionVeinticuatroUiState())
    val uiState = _uiState.asStateFlow()

    fun onSliderChanged(newValue: Float) {
        _uiState.update { it.copy(sliderValue = newValue) }
    }

    fun onSteppedSliderChanged(newValue: Float) {
        _uiState.update { it.copy(steppedSliderValue = newValue) }
    }

    fun onRangeSliderChanged(newRange: ClosedFloatingPointRange<Float>) {
        _uiState.update { it.copy(rangeSliderValue = newRange) }
    }
}