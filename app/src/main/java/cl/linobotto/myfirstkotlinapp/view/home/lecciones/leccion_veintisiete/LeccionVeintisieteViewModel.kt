package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintisiete

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LeccionVeintisieteViewModel : ViewModel() {

    private val _showBasicDialog = MutableStateFlow(false)
    val showBasicDialog = _showBasicDialog.asStateFlow()

    private val _showCustomDialog = MutableStateFlow(false)
    val showCustomDialog = _showCustomDialog.asStateFlow()

    fun onBasicDialogClicked() {
        _showBasicDialog.value = true
    }

    fun onCustomDialogClicked() {
        _showCustomDialog.value = true
    }

    fun onDialogDismiss() {
        _showBasicDialog.value = false
        _showCustomDialog.value = false
    }
}