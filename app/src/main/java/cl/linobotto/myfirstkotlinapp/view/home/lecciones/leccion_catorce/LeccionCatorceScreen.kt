package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_catorce

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Preview(showBackground = true, heightDp = 20000)
@Composable
fun LeccionCatorcePreview(){
    LeccionCatorceScreen(navController = rememberNavController())
}

@Composable
fun LeccionCatorceScreen(
    navController: NavController,
    vm: LeccionCatorceViewModel = viewModel()
){
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "14. MVVM + Validación dinámica"
            )
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.kodee_winter),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))

            CampoConError(
                label = "Nombre",
                value = uiState.nombre.value,
                error = uiState.nombre.error,
                onValueChange = vm::onNombreChange
            )
            Spacer(modifier = Modifier.height(8.dp))
            CampoConError(
                label = "Email",
                value = uiState.email.value,
                error = uiState.email.error,
                onValueChange = vm::onEmailChange
            )
            Spacer(modifier = Modifier.height(8.dp))
            CampoConError(
                label = "Contraseña",
                value = uiState.password.value,
                error = uiState.password.error,
                onValueChange = vm::onPasswordChange
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { vm.confirmar() },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.isAllValid
            ) {
                Text(text = if (uiState.isAllValid) "Enviar" else "Completa los campos")
            }

            if (uiState.confirmationMessage != null){
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = uiState.confirmationMessage!!, color = MaterialTheme.colorScheme.primary)
            }

            Spacer(modifier = Modifier.height(24.dp))
            SeccionExplicacionLeccionCatorce()
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.navigate(Home) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Volver al inicio")
            }
        }
    }
}

@Composable
private fun CampoConError(
    label: String,
    value: String,
    error: String?,
    onValueChange: (String) -> Unit
){
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            isError = error != null
        )
        if (error != null){
            Text(
                text = error,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
private fun SeccionExplicacionLeccionCatorce(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "¿Qué hicimos en esta lección? (MVVM + validación dinámica)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = "Creamos un ViewModel con un UiState compuesto por FieldState (valor + error) y funciones de validación. Cada cambio en un TextField dispara su handler, valida y actualiza el estado. El botón solo se habilita cuando todo es válido."
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            style = MaterialTheme.typography.titleSmall,
            text = "1) Data classes (resultado de validación, estado de campo y UiState)"
        )
        Spacer(modifier = Modifier.height(6.dp))
        CajaCodigo(
            codigo = """
                // Resultado de una validación
                data class ValidationResult(
                    val isValid: Boolean,
                    val errorMessage: String? = null
                )

                // Estado de un campo individual
                data class FieldState(
                    val value: String = "",
                    val error: String? = null
                )

                // Estado de pantalla
                data class LeccionCatorceUiState(
                    val nombre: FieldState = FieldState(),
                    val email: FieldState = FieldState(),
                    val password: FieldState = FieldState(),
                    val isAllValid: Boolean = false,
                    val confirmationMessage: String? = null
                )
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            style = MaterialTheme.typography.titleSmall,
            text = "2) Validaciones aplicadas"
        )
        Spacer(modifier = Modifier.height(6.dp))
        CajaCodigo(
            codigo = """
                private fun validateNombre(nombre: String): ValidationResult {
                    return if (nombre.isBlank()) {
                        ValidationResult(false, "El nombre no puede estar vacío")
                    } else ValidationResult(true, null)
                }

                private fun validateEmail(email: String): ValidationResult {
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
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            style = MaterialTheme.typography.titleSmall,
            text = "3) Handlers (onChange) que validan y actualizan UiState"
        )
        Spacer(modifier = Modifier.height(6.dp))
        CajaCodigo(
            codigo = """
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

                private fun isAllValid(nombre: FieldState, email: FieldState, password: FieldState): Boolean {
                    return nombre.error == null && nombre.value.isNotBlank() &&
                           email.error == null && email.value.isNotBlank() &&
                           password.error == null && password.value.isNotBlank()
                }
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            style = MaterialTheme.typography.titleSmall,
            text = "4) UI: TextField con error y botón habilitado solo si todo es válido"
        )
        Spacer(modifier = Modifier.height(6.dp))
        CajaCodigo(
            codigo = """
                @Composable
                fun Pantalla(){
                    val vm: LeccionCatorceViewModel = viewModel()
                    val uiState by vm.uiState.collectAsStateWithLifecycle()

                    OutlinedTextField(
                        value = uiState.email.value,
                        onValueChange = vm::onEmailChange,
                        isError = uiState.email.error != null,
                        label = { Text("Email") }
                    )
                    if (uiState.email.error != null) {
                        Text(text = uiState.email.error!!, color = MaterialTheme.colorScheme.error)
                    }

                    Button(
                        onClick = { vm.confirmar() },
                        enabled = uiState.isAllValid
                    ) { Text("Enviar") }
                }
            """.trimIndent()
        )
    }
}

@Composable
private fun CajaCodigo(codigo: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF424242))
            .padding(16.dp)
    ){
        Text(
            text = codigo,
            style = TextStyle(fontSize = 14.sp, color = Color.White)
        )
    }
}
