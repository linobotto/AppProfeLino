package cl.linobotto.myfirstkotlinapp.view.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState
}
data class HomeUiState(
    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList()
)

