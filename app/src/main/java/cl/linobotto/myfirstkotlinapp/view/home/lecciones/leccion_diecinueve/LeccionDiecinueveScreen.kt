package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_diecinueve

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Composable
fun LeccionDiecinueveScreen(navController: NavController) {
    val context = LocalContext.current

    fun abrirGoogleMaps() {
        // Puedes usar coordenadas o una búsqueda. Aquí un ejemplo de búsqueda de un lugar.
        val uri = "google.navigation:q=Avenida+Libertador+Bernardo+O'Higgins+Santiago+Chile"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        context.startActivity(intent)
    }

    fun abrirTelefono() {
        // Abre el marcador con el número cargado (no requiere permiso de llamada)
        val number = "tel:+56912345678"
        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse(number))
        if (dialIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(dialIntent)
        }
    }

    fun abrirWhatsApp() {
        // Usamos ACTION_SENDTO con esquema smsto: para abrir chat con número
        val phone = "+56912345678" // reemplazar por el que necesites
        val uri = Uri.parse("smsto:$phone")
        val sendIntent = Intent(Intent.ACTION_SENDTO, uri).apply {
            `package` = "com.whatsapp"
        }
        try {
            context.startActivity(sendIntent)
        } catch (e: ActivityNotFoundException) {
            // WhatsApp no instalado: intentamos abrir en Play Store
            val playStore = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
            )
            if (playStore.resolveActivity(context.packageManager) != null) {
                context.startActivity(playStore)
            }
        }
    }

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
                text = "Lección 19: Intents a Google Maps, Teléfono y WhatsApp",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.kodee_auto_fondo_wide),
                contentDescription = null
            )
            Spacer(Modifier.height(12.dp))

            SectionBox(title = "¿Qué aprenderás?") {
                Text(
                    text = "Cómo crear botones que abran otras apps instaladas usando Intents: Google Maps para mostrar un lugar, el marcador telefónico con un número, y WhatsApp para iniciar un chat.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Demostración") {
                Button(onClick = { abrirGoogleMaps() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Abrir Google Maps")
                }
                Spacer(Modifier.height(8.dp))
                Button(onClick = { abrirTelefono() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Abrir Teléfono (Marcador)")
                }
                Spacer(Modifier.height(8.dp))
                Button(onClick = { abrirWhatsApp() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Abrir WhatsApp (chat)")
                }
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Código: abrir Google Maps") {
                CajaCodigo(
                    codigo = """
                        // 1) Abrir Google Maps con búsqueda
                        val gmmIntentUri = Uri.parse("geo:0,0?q=Plaza+de+Armas+Santiago")
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                            setPackage("com.google.android.apps.maps")
                        }
                        if (mapIntent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(mapIntent)
                        } else {
                            // Fallback a cualquier app que soporte geo:
                            val fallback = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                            if (fallback.resolveActivity(context.packageManager) != null) {
                                context.startActivity(fallback)
                            }
                        }
                        
                        // O para navegación en auto hacia una dirección:
                        // val navUri = Uri.parse("google.navigation:q=Plaza+de+Armas+Santiago")
                        // val navIntent = Intent(Intent.ACTION_VIEW, navUri).apply { setPackage("com.google.android.apps.maps") }
                        // context.startActivity(navIntent)
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Código: abrir Teléfono (sin permisos)") {
                CajaCodigo(
                    codigo = """
                        // 2) Abrir el marcador con un número pre-cargado
                        val number = "tel:+56912345678"
                        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse(number))
                        if (dialIntent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(dialIntent)
                        }
                        // Nota: ACTION_DIAL no requiere permisos. Para llamar directo (ACTION_CALL) sí necesitas permisos.
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Código: abrir WhatsApp") {
                CajaCodigo(
                    codigo = """
                        // 3) Abrir chat de WhatsApp con ACTION_SENDTO y esquema smsto:
                        val phone = "+56912345678"
                        val uri = Uri.parse("smsto:${'$'}phone")
                        val sendIntent = Intent(Intent.ACTION_SENDTO, uri).apply {
                            `package` = "com.whatsapp"
                        }
                        try {
                            context.startActivity(sendIntent)
                        } catch (e: ActivityNotFoundException) {
                            // Si no está instalado, abrir Play Store como alternativa
                            val playStore = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                            )
                            if (playStore.resolveActivity(context.packageManager) != null) {
                                context.startActivity(playStore)
                            }
                        }

                        // Alternativa usando API de WhatsApp (puede variar):
                        // val url = "https://wa.me/56912345678?text=" + Uri.encode("Hola!")
                        // context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    """.trimIndent()
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
