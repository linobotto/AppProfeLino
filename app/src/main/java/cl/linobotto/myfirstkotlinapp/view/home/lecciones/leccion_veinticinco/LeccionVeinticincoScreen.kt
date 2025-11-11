
package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veinticinco

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Composable
fun LeccionVeinticincoScreen(navController: NavController) {

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Lección 25: Permisos y Características",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
                contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(R.drawable.permises_phone),
                    modifier = Modifier.height(120.dp),
                    contentDescription = "Personaje pidiendo permisos a un telefono"
                )
            }
            Spacer(Modifier.height(16.dp))

            SectionBox(title = "1. ¿Qué son los Permisos?") {
                Text(
                    """Los permisos en Android son un mecanismo de seguridad para proteger la privacidad del usuario. Las aplicaciones deben solicitar permiso para acceder a datos sensibles o a ciertas características del sistema."""
                )
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "2. Declarar Permisos en AndroidManifest.xml") {
                Text(
                    """Antes de poder solicitar un permiso, debes declararlo en `AndroidManifest.xml`. Estos son los que usaremos en los ejemplos:"""
                )
                CajaCodigo(
                    codigo = """
                        <uses-permission android:name="android.permission.CAMERA" />
                        <uses-permission android:name="android.permission.BLUETOOTH_SCAN" />
                        <uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "3. Filtrado en Google Play con <uses-feature>") {
                Text(
                    """Esta etiqueta declara el hardware o software que tu app necesita. Su propósito principal es ser usada por servicios como Google Play para filtrar tu app de dispositivos que no cumplen los requisitos."""
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    """El atributo `android:required` es clave:
• `true`: La app no puede funcionar sin la característica. Google Play bloqueará la instalación en dispositivos incompatibles.
• `false`: La app prefiere usar la característica, pero puede funcionar sin ella."""
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    """Importante: A veces, un `<uses-permission>` (como el de la cámara) puede hacer que Google Play asuma que la característica es requerida. Si tu función es opcional, debes añadir explícitamente `<uses-feature>` con `required="false"` para evitar que tu app sea filtrada innecesariamente."""
                )
                CajaCodigo(
                    codigo = """<!-- Ejemplo: La cámara es útil, pero no obligatoria -->
<uses-feature
    android:name="android.hardware.camera"
    android:required="false" />"""
                )
            }

            Image(
                painter = painterResource(R.drawable.permisos),
                modifier = Modifier.height(120.dp),
                contentDescription = "Personaje con una lista de permisos"
            )

            Spacer(Modifier.height(16.dp))
            SectionBox(title = "4. Solicitar Permisos en Tiempo de Ejecución (Jetpack Compose)") {
                Text(
                    """Desde Android 6.0 (API 23), los permisos considerados 'peligrosos' deben ser solicitados en tiempo de ejecución. En Jetpack Compose, puedes usar el contrato `ActivityResultContracts.RequestPermission`."""
                )
                Spacer(Modifier.height(8.dp))
                CajaCodigo(
                    codigo = """
                        // 1. Prepara el lanzador de la solicitud de permiso
                        val requestPermissionLauncher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.RequestPermission()
                        ) { isGranted: Boolean ->
                            if (isGranted) {
                                // Permiso concedido. Puedes usar la función.
                            } else {
                                // Permiso denegado.
                            }
                        }

                        // 2. Lanza la solicitud
                        Button(onClick = { requestPermissionLauncher.launch(Manifest.permission.CAMERA) }) {
                            Text("Solicitar permiso de cámara")
                        }
                    """.trimIndent()
                )
            }
            Spacer(Modifier.height(16.dp))

            SectionBox(title = "5. Ejemplo: Permiso de Cámara") {
                Text(
                    """A continuación, un ejemplo real solicitando el permiso de la cámara."""
                )

                Spacer(Modifier.height(8.dp))

                var cameraPermissionStatus by remember { mutableStateOf("Aún no solicitado") }
                val requestPermissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission()
                ) { isGranted: Boolean ->
                    cameraPermissionStatus = if (isGranted) "CONCEDIDO" else "DENEGADO"
                }

                Button(onClick = { requestPermissionLauncher.launch(Manifest.permission.CAMERA) }) {
                    Text("Solicitar permiso de cámara")
                }
                Spacer(Modifier.height(8.dp))
                Text(text = "Estado: $cameraPermissionStatus")
            }
            Spacer(Modifier.height(16.dp))
            SectionBox(title = "6. Permisos para Dispositivos Cercanos (Bluetooth/Wi-Fi)") {
                Text(
                    """Desde Android 12 (API 31), se requieren permisos específicos para escanear dispositivos Bluetooth o Wi-Fi sin necesidad de solicitar la ubicación precisa. Esto mejora la privacidad del usuario."""
                )
                Spacer(Modifier.height(8.dp))
                CajaCodigo(
                    codigo = """
                        // 1. Declara los permisos en AndroidManifest.xml
                        <uses-permission android:name="android.permission.BLUETOOTH_SCAN" />
                        <uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />

                        // 2. Prepara el lanzador para múltiples permisos
                        val permissions = arrayOf(
                            Manifest.permission.BLUETOOTH_SCAN,
                            Manifest.permission.BLUETOOTH_CONNECT
                        )

                        val requestMultiplePermissionsLauncher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.RequestMultiplePermissions()
                        ) { permissionsResult ->
                            permissionsResult.forEach { (permission, isGranted) ->
                                if (isGranted) {
                                    // Permiso concedido
                                } else {
                                    // Permiso denegado
                                }
                            }
                        }

                        // 3. Lanza la solicitud
                        Button(onClick = { requestMultiplePermissionsLauncher.launch(permissions) }) {
                            Text("Buscar dispositivos cercanos")
                        }
                    """.trimIndent()
                )
            }
            Spacer(Modifier.height(16.dp))
            SectionBox(title = "7. Ejemplo: Permisos para Dispositivos Cercanos") {
                Text(
                    """Desde Android 12 (API 31), se requieren permisos específicos para Bluetooth. Aquí un ejemplo funcional:"""
                )

                Spacer(Modifier.height(8.dp))

                var btScanStatus by remember { mutableStateOf("Aún no solicitado") }
                var btConnectStatus by remember { mutableStateOf("Aún no solicitado") }

                val permissionsToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    arrayOf(
                        Manifest.permission.BLUETOOTH_SCAN,
                        Manifest.permission.BLUETOOTH_CONNECT,
                    )
                } else {
                    emptyArray()
                }

                val multiplePermissionsLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions()
                ) { permissionsResult ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        btScanStatus = if (permissionsResult[Manifest.permission.BLUETOOTH_SCAN] == true) "CONCEDIDO" else "DENEGADO"
                        btConnectStatus = if (permissionsResult[Manifest.permission.BLUETOOTH_CONNECT] == true) "CONCEDIDO" else "DENEGADO"
                    }
                }

                Button(onClick = {
                    if (permissionsToRequest.isNotEmpty()) {
                        multiplePermissionsLauncher.launch(permissionsToRequest)
                    } else {
                        btScanStatus = "No requerido en esta versión"
                        btConnectStatus = "No requerido en esta versión"
                    }
                }) {
                    Text("Buscar dispositivos cercanos")
                }
                Spacer(Modifier.height(8.dp))
                Text("Estado BLUETOOTH_SCAN: $btScanStatus")
                Text("Estado BLUETOOTH_CONNECT: $btConnectStatus")
            }

            Spacer(Modifier.height(24.dp))

            Button(onClick = { navController.navigate(Home) }, modifier = Modifier.fillMaxWidth()) {
                Text("Volver a las lecciones")
            }
        }
    }
}

@Composable
private fun SectionBox(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(12.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        content()
    }
}

@Composable
private fun CajaCodigo(codigo: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1E1E1E))
            .padding(12.dp)
    ) {
        Text(
            text = codigo,
            style = TextStyle(
                color = Color(0xFFD4D4D4),
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        )
    }
}
