package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintiseis

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home
import com.journeyapps.barcodescanner.CompoundBarcodeView
import cl.linobotto.myfirstkotlinapp.R

@Composable
fun LeccionVeintiseisScreen(navController: NavController) {

    var hasCameraPermission by remember { mutableStateOf(false) }
    var scanResult by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasCameraPermission = isGranted
        }
    )

    LaunchedEffect(key1 = true) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            hasCameraPermission = true
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

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
                text = "Lección 26: Uso de Hardware (Cámara)",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.kodee_camera),
                    modifier = Modifier.height(120.dp),
                    contentDescription = "QR Code",
                )
            }

            SectionBox(title = "1. Integrando Vistas de Android con AndroidView") {
                Text(
                    """Jetpack Compose está diseñado para interoperar con el sistema de Vistas de Android clásico. Para usar una Vista tradicional (como MapView, WebView o, en este caso, una vista de cámara) dentro de un Composable, usamos el componente `AndroidView`."""
                )
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "2. Implementación del Escáner QR") {
                Text("Para el escáner, usamos la librería ZXing. La envolvemos en un `AndroidView` y configuramos un callback para recibir el resultado.")
                CajaCodigo(
                    codigo = """
                        // 1. Añade la dependencia en build.gradle.kts
                        // implementation("com.journeyapps:zxing-android-embedded:4.3.0")

                        // 2. Usa AndroidView en tu Composable
                        AndroidView(
                            factory = { context ->
                                // Crea la vista de la cámara
                                CompoundBarcodeView(context).apply {
                                    // Configura un callback para un único escaneo
                                    decodeSingle { result ->
                                        scanResult = result.text
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth().height(400.dp)
                        )
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "3. Ejemplo en Vivo: Escáner de QR") {
                if (hasCameraPermission) {
                    Text("Apunte la cámara a un código QR")
                    Spacer(modifier = Modifier.height(8.dp))
                    AndroidView(
                        factory = { ctx ->
                            CompoundBarcodeView(ctx).apply {
                                decodeSingle { result ->
                                    scanResult = result.text
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (scanResult != null) "Resultado: $scanResult" else "Esperando resultado...",
                        style = MaterialTheme.typography.bodyLarge
                    )
                } else {
                    Text("Se necesita permiso de la cámara para que esta función se active.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) }) {
                        Text("Solicitar permiso de cámara")
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "4. ¿Qué otro hardware se puede usar?") {
                Text(
                    """Además de la cámara, puedes acceder a otro hardware del dispositivo, generalmente a través de los managers del sistema de Android:

• GPS: `LocationManager` para obtener la ubicación.
• NFC: `NfcAdapter` para comunicación de campo cercano.
• Sensores: `SensorManager` para el acelerómetro, giroscopio, sensor de luz, etc.
• Biometría: `BiometricPrompt` para autenticación con huella o rostro."""
                )
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
