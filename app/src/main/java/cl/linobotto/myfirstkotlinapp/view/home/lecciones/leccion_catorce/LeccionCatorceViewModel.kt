package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_catorce

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

// Resultado de una validación para un campo
data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)

// Estado de un campo: valor + posible error a mostrar
data class FieldState(
    val value: String = "",
    val error: String? = null
)

// Estado de pantalla para Lección 14
data class LeccionCatorceUiState(
    val nombre: FieldState = FieldState(),
    val email: FieldState = FieldState(),
    val password: FieldState = FieldState(),
    val isAllValid: Boolean = false,
    val confirmationMessage: String? = null
)

class LeccionCatorceViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LeccionCatorceUiState())
    val uiState: StateFlow<LeccionCatorceUiState> = _uiState

    fun onNombreChange(value: String){
        val result = validateNombre(value)
        _uiState.update {
            val newNombre = FieldState(value = value, error = result.errorMessage)
            it.copy(
                nombre = newNombre,
                isAllValid = isAllValid(newNombre, it.email, it.password),
                confirmationMessage = null
            )
        }
    }

    fun onEmailChange(value: String){
        val result = validateEmail(value)
        _uiState.update {
            val newEmail = FieldState(value = value, error = result.errorMessage)
            it.copy(
                email = newEmail,
                isAllValid = isAllValid(it.nombre, newEmail, it.password),
                confirmationMessage = null
            )
        }
    }

    fun onPasswordChange(value: String){
        val result = validatePassword(value)
        _uiState.update {
            val newPwd = FieldState(value = value, error = result.errorMessage)
            it.copy(
                password = newPwd,
                isAllValid = isAllValid(it.nombre, it.email, newPwd),
                confirmationMessage = null
            )
        }
    }

    fun confirmar(){
        val s = _uiState.value
        val allOk = isAllValid(s.nombre, s.email, s.password)
        _uiState.update {
            it.copy(
                isAllValid = allOk,
                confirmationMessage = if (allOk) "Formulario válido ✅" else null
            )
        }
    }

    // Validaciones
    private fun validateNombre(nombre: String): ValidationResult {
        return if (nombre.isBlank()) {
            ValidationResult(false, "El nombre no puede estar vacío")
        } else ValidationResult(true, null)
    }

    private fun validateEmail(email: String): ValidationResult {
        // Patrón simple de email (no perfecto, suficiente para la demo)
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return if (emailRegex.matches(email)) {
            ValidationResult(true, null)
        } else {
            ValidationResult(false, "Formato de email inválido")
        }
    }

    private fun validatePassword(password: String): ValidationResult {
        val hasUppercase = password.any { it.isUpperCase() }
        val hasDigit = password.any { it.isDigit() }
        return if (!hasUppercase || !hasDigit) {
            ValidationResult(false, "La contraseña debe tener al menos 1 mayúscula y 1 número")
        } else {
            ValidationResult(true, null)
        }
    }

    private fun isAllValid(nombre: FieldState, email: FieldState, password: FieldState): Boolean {
        return nombre.error == null && nombre.value.isNotBlank() &&
                email.error == null && email.value.isNotBlank() &&
                password.error == null && password.value.isNotBlank()
    }
}
