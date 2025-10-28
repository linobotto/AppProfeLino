package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_dieciocho

import android.media.AudioAttributes
import android.media.MediaPlayer
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Composable
fun LeccionDieciochoScreen(navController: NavController) {
    // Reproducir localSound (terror.mp3) de fondo automáticamente
    val context = LocalContext.current
    val backgroundPlayer = remember {
        MediaPlayer.create(context, R.raw.terror).apply {
            isLooping = true
        }
    }
    LaunchedEffect(Unit) {
        if (!backgroundPlayer.isPlaying) backgroundPlayer.start()
    }
    DisposableEffect(Unit) {
        onDispose {
            try {
                if (backgroundPlayer.isPlaying) backgroundPlayer.stop()
            } catch (_: Exception) {}
            backgroundPlayer.release()
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
                text = "Lección 18: Reproducir sonidos (MediaPlayer + Compose)",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.kodee_scream),
                contentDescription = null
            )

            Spacer(Modifier.height(8.dp))
            SectionBox(title = "¿Qué haremos?") {
                Text(
                    text = "Aprenderás a reproducir un audio desde una URL y también sonidos locales (raw) usando MediaPlayer dentro de un Composable. Veremos cómo reproducir en segundo plano y cómo lanzar un sonido puntual con botón.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Ejemplo funcionando") {
                // Botón para reproducir localScream (raw)
                PlayLocalScreamButton()
                Spacer(Modifier.height(8.dp))
                // Ejemplo original: reproducir desde URL
                PlaySoundButton()
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Permisos necesarios") {
                Text("Para audio local (carpeta raw) no se requieren permisos. Para audio por internet, asegúrate de tener en el AndroidManifest:", style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray)
                CajaCodigo(codigo = "<uses-permission android:name=\"android.permission.INTERNET\" />")
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Código esencial (Compose + MediaPlayer)") {
                CajaCodigo(
                    codigo = """
                        // 1) Sonido local en segundo plano (sin botón)
                        @Composable
                        fun BackgroundLocalSound() {
                            val context = LocalContext.current
                            val player = remember { MediaPlayer.create(context, R.raw.terror).apply { isLooping = true } }
                            LaunchedEffect(Unit) { if (!player.isPlaying) player.start() }
                            DisposableEffect(Unit) { onDispose { try { if (player.isPlaying) player.stop() } catch (_: Exception) {} ; player.release() } }
                        }

                        // 2) Botón para reproducir un sonido local puntual (scream)
                        @Composable
                        fun PlayLocalScreamButton() {
                            val context = LocalContext.current
                            Button(onClick = {
                                val mp = MediaPlayer.create(context, R.raw.scream)
                                mp.setOnCompletionListener { it.release() }
                                mp.start()
                            }) {
                                Text("Reproducir localScream")
                            }
                        }

                        // 3) (Opcional) Botón para reproducir desde URL
                        @Composable
                        fun PlaySoundButton() {
                            val context = LocalContext.current
                            val url = \"https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3\"
                            val mediaPlayer = remember { MediaPlayer() }
                            DisposableEffect(Unit) { onDispose { mediaPlayer.release() } }
                            Button(onClick = {
                                try {
                                    mediaPlayer.reset()
                                    mediaPlayer.setAudioAttributes(
                                        AudioAttributes.Builder()
                                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                            .build()
                                    )
                                    mediaPlayer.setDataSource(context, Uri.parse(url))
                                    mediaPlayer.setOnPreparedListener { it.start() }
                                    mediaPlayer.prepareAsync()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }) { Text(text = \"Reproducir sonido (URL)\") }
                        }
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
private fun PlaySoundButton() {
    val context = LocalContext.current
    val url = "https://audio-previews.elements.envatousercontent.com/files/349781807/preview.mp3"


    // Guardamos una única instancia para controlar su ciclo de vida
    val mediaPlayer = remember { MediaPlayer() }

    // Liberamos el recurso cuando el Composable salga de composición
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    Button(onClick = {
        try {
            mediaPlayer.reset()
            mediaPlayer.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            mediaPlayer.setDataSource(context, Uri.parse(url))
            mediaPlayer.setOnPreparedListener { it.start() }
            mediaPlayer.prepareAsync()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Reproducir sonido grito online")
    }
}

@Composable
private fun SectionBox(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFEFF3F8))
            .padding(16.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, color = Color(0xFF1E1E1E))
        Spacer(modifier = Modifier.height(8.dp))
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
            style = TextStyle(color = Color.White)
        )
    }
}


@Composable
private fun PlayLocalScreamButton() {
    val context = LocalContext.current
    Button(onClick = {
        try {
            val mp = MediaPlayer.create(context, R.raw.scream)
            mp.setOnCompletionListener { it.release() }
            mp.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Reproducir sonido grito local")
    }
}
