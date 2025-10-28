package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_diecisiete

import android.view.ViewGroup
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun LeccionDiecisieteScreen(navController: NavController){
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
                text = "Lección 17: YouTubePlayerView en Jetpack Compose",
                style = MaterialTheme.typography.titleLarge,
            )
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.kodee_sitting),
                contentDescription = null
            )
            Spacer(Modifier.height(8.dp))
            SectionBox(title = "¿Qué haremos?") {
                Text(
                    text = "Integraremos un video de YouTube dentro de una pantalla Compose empleando AndroidView para alojar un YouTubePlayerView (de la librería AndroidYouTubePlayer). Es un enfoque similar a usar ExoPlayer, pero reutiliza una View clásica.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray,
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Ejemplo funcionando") {
                // Cambiado al video solicitado por el usuario
                YouTubePlayerComposable(videoId = "d4nlri_6TxY")
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Cómo agregar la librería") {
                Text("1) En libs.versions.toml agrega la dependencia:", style = MaterialTheme.typography.bodyMedium,color = Color.DarkGray)
                CajaCodigo(
                    codigo = "[versions] \n" +
                            "youtubePlayer = \"13.0.0\"" +
                            "\n [libraries] \n" +
                            "android-youtube-player = { group = \"com.pierfrancescosoffritti.androidyoutubeplayer\", name = \"core\", version.ref = \"youtubePlayer\" }\n "
                )
                Text("2) En AndroidManifest.xml agrega el permiso de Internet:", style = MaterialTheme.typography.bodyMedium,color = Color.DarkGray)
                CajaCodigo(codigo = "<uses-permission android:name=\"android.permission.INTERNET\" />")
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Cómo funciona") {
                Text(
                    text = "1) Creamos un YouTubePlayerView y lo registramos al ciclo de vida para manejar pausa/reanudar.\n" +
                            "2) Escuchamos onReady para cargar el video (loadVideo(videoId, 0f)).\n" +
                            "3) Usamos AndroidView para insertar la View clásica en Compose.\n" +
                            "4) Liberación de recursos: el propio YouTubePlayerView se maneja como LifecycleObserver.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Código esencial (Compose + AndroidView)") {
                CajaCodigo(
                    codigo = "@Composable\n" +
                            "private fun YouTubePlayerComposable(videoId: String){\n" +
                            "    val activity = LocalLifecycleOwner.current\n" +
                            "\n" +
                            "    AndroidView(\n" +
                            "        modifier = Modifier\n" +
                            "            .fillMaxWidth()\n" +
                            "            .height(220.dp),\n" +
                            "        factory = { context ->\n" +
                            "            val youTubePlayerView = YouTubePlayerView(context).apply {\n" +
                            "                layoutParams = ViewGroup.LayoutParams(\n" +
                            "                    ViewGroup.LayoutParams.MATCH_PARENT,\n" +
                            "                    ViewGroup.LayoutParams.WRAP_CONTENT\n" +
                            "                )\n" +
                            "                enableAutomaticInitialization = false\n" +
                            "            }\n" +
                            "\n" +
                            "            val listener = object : AbstractYouTubePlayerListener() {\n" +
                            "                override fun onReady(youTubePlayer: YouTubePlayer) {\n" +
                            "                    // Carga el video y lo empieza a reproducir desde el segundo 0\n" +
                            "                    youTubePlayer.loadVideo(videoId, 0f)\n" +
                            "                }\n" +
                            "\n" +
                            "                override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {\n" +
                            "                    // Aquí puedes reaccionar a cambios de estado: PLAYING, PAUSED, ENDED, etc\n" +
                            "                }\n" +
                            "            }\n" +
                            "\n" +
                            "            val options = IFramePlayerOptions.Builder(\n" +
                            "                context = context\n" +
                            "            )\n" +
                            "                .controls(1) // muestra controles nativos de YouTube\n" +
                            "                .build()\n" +
                            "\n" +
                            "            youTubePlayerView.initialize(listener, options)\n" +
                            "\n" +
                            "            // Importante: asociar el ciclo de vida para pausar/reanudar correctamente\n" +
                            "            activity.lifecycle.addObserver(youTubePlayerView)\n" +
                            "\n" +
                            "            youTubePlayerView\n" +
                            "        },\n" +
                            "        update = { /* no-op por ahora */ }\n" +
                            "    )\n" +
                            "\n" +
                            "    // Liberar recursos cuando este Composable salga de composición\n" +
                            "    DisposableEffect(Unit) {\n" +
                            "        onDispose {\n" +
                            "            // Nada específico aquí porque el release se maneja por el lifecycle observer\n" +
                            "            // Si hubiéramos guardado una referencia, podríamos llamar a release()\n" +
                            "        }\n" +
                            "    }\n" +
                            "}"
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
private fun YouTubePlayerComposable(videoId: String){
    val activity = LocalLifecycleOwner.current

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        factory = { context ->
            val youTubePlayerView = YouTubePlayerView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                enableAutomaticInitialization = false
            }

            val listener = object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    // Carga el video y lo empieza a reproducir desde el segundo 0
                    youTubePlayer.loadVideo(videoId, 0f)
                }

                override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                    // Aquí puedes reaccionar a cambios de estado: PLAYING, PAUSED, ENDED, etc
                }
            }

            val options = IFramePlayerOptions.Builder(
                context = context
            )
                .controls(1) // muestra controles nativos de YouTube
                .build()

            youTubePlayerView.initialize(listener, options)

            // Importante: asociar el ciclo de vida para pausar/reanudar correctamente
            activity.lifecycle.addObserver(youTubePlayerView)

            youTubePlayerView
        },
        update = { /* no-op por ahora */ }
    )

    // Liberar recursos cuando este Composable salga de composición
    DisposableEffect(Unit) {
        onDispose {
            // Nada específico aquí porque el release se maneja por el lifecycle observer
            // Si hubiéramos guardado una referencia, podríamos llamar a release()
        }
    }
}
