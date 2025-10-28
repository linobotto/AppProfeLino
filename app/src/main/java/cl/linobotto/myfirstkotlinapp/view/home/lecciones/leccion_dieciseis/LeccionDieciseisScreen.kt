package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_dieciseis

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cl.linobotto.myfirstkotlinapp.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Preview(showBackground = true)
@Composable
fun LeccionDieciseisPreview() {
    LeccionDieciseisScreen(navController = rememberNavController())
}

@Composable
fun LeccionDieciseisScreen(navController: NavController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "16. Animaciones en Compose"
            )
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.kodee_greeting),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                SeccionExplicacionAnimaciones()
                Spacer(modifier = Modifier.height(24.dp))
                DemoAnimatedVisibility()
                Spacer(modifier = Modifier.height(16.dp))
                DemoAnimateFloatAsStateLike()
                Spacer(modifier = Modifier.height(16.dp))
                DemoInfiniteTransitionPulse()
                Spacer(modifier = Modifier.height(16.dp))
                DemoCrossfadeImages()
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { navController.navigate(Home) },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text(text = "Volver a las lecciones") }
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
private fun DemoAnimatedVisibility() {
    var visible by remember { mutableStateOf(true) }
    SectionBox(title = "AnimatedVisibility: mostrar/ocultar con animación") {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { visible = !visible }) {
                Text(if (visible) "Ocultar" else "Mostrar")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text("Toque el botón para alternar", style = TextStyle(color = Color.DarkGray))
        }
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedVisibility(visible = visible) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFBBDEFB)),
                contentAlignment = Alignment.Center
            ) {
                Text("¡Hola! Soy visible ✨", style = TextStyle(color = Color.DarkGray))
            }
        }
    }
}

@Composable
private fun DemoAnimateFloatAsStateLike() {
    var big by remember { mutableStateOf(false) }
    SectionBox(title = "Animación de escala/rotación con transición") {
        val target = if (big) 1.0f else 0f
        val infinite = rememberInfiniteTransition()
        val rotation by infinite.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 4000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
        Button(onClick = { big = !big }) {
            Text(if (big) "Restaurar" else "Ampliar")
        }
        Spacer(modifier = Modifier.height(8.dp))
        val animated by animateFloatAsState(
            targetValue = target,
            animationSpec = tween(durationMillis = 600)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.kodee_waving),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp + (animated * 60).dp)
                    .rotate(rotation)
            )
        }
        Text("Rotación infinita + cambio de tamaño al pulsar", style = TextStyle(color = Color.DarkGray))
    }
}

@Composable
private fun DemoInfiniteTransitionPulse() {
    SectionBox(title = "Pulsación infinita (scale)") {
        val infinite = rememberInfiniteTransition()
        val scale by infinite.animateFloat(
            initialValue = 0.9f,
            targetValue = 1.1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.kodee_in_love),
                contentDescription = null,
                modifier = Modifier.size(96.dp).scale(scale)
            )
        }
        Text("Animación continua usando rememberInfiniteTransition", style = TextStyle(color = Color.DarkGray))
    }
}

@Composable
private fun DemoCrossfadeImages() {
    var first by remember { mutableStateOf(true) }
    SectionBox(title = "Crossfade entre imágenes") {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { first = !first }) { Text("Alternar") }
            Spacer(modifier = Modifier.width(12.dp))
            Text("Desvanece entre recursos gráficos", style = TextStyle(color = Color.DarkGray))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Crossfade(targetState = first) { isFirst ->
            val res = if (isFirst) R.drawable.kodee_regular else R.drawable.kodee_jumping
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(painter = painterResource(id = res), contentDescription = null, modifier = Modifier.size(120.dp))
            }
        }
    }
}

@Composable
private fun SeccionExplicacionAnimaciones() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "¿Qué son las animaciones en Compose?", style = MaterialTheme.typography.titleMedium)
        Text(
            text = "Compose genera animaciones valores de estado de forma declarativa. Cambias el estado, y la UI interpola entre el estado anterior y el nuevo usando especificaciones (tween, spring, keyframes).",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Propiedades principales", style = MaterialTheme.typography.titleSmall)
        Text(
            text = "• animate*AsState: anima un valor simple cuando su estado objetivo cambia.\n" +
                    "• updateTransition: coordina múltiples targets/propiedades.\n" +
                    "• AnimatedVisibility: muestra/oculta con enter/exit.\n" +
                    "• rememberInfiniteTransition: animaciones repetitivas sin fin.\n" +
                    "• Crossfade: cambia entre pantallas/estados con desvanecido.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        SeccionPasoAPasoLibreriasAnimacion()
        Spacer(modifier = Modifier.height(24.dp))
        SeccionTweenYSpring()
        Spacer(modifier = Modifier.height(24.dp))

        Spacer(modifier = Modifier.height(24.dp))
        Text("Ejemplo: animateFloatAsState (básico)", style = MaterialTheme.typography.titleMedium)
        CajaCodigo(
            codigo = """
                @Composable
                fun BotonEscala() {
                    var grande by remember { mutableStateOf(false) }
                    val escala by animateFloatAsState(
                        targetValue = if (grande) 1.2f else 1f,
                        animationSpec = tween(300),
                        label = "escala"
                    )
                    Button(onClick = { grande = !grande }) {
                        Text("Toggle")
                    }
                    Box(Modifier.scale(escala)) { /* contenido */ }
                }
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Ejemplo: AnimatedVisibility", style = MaterialTheme.typography.titleSmall)
        CajaCodigo(
            codigo = """
                @Composable
                fun PanelVisible() {
                    var visible by remember { mutableStateOf(true) }
                    AnimatedVisibility(visible) {
                        /* contenido que entra/sale con animación */
                    }
                }
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Ejemplo: rememberInfiniteTransition (pulso)", style = MaterialTheme.typography.titleSmall)
        CajaCodigo(
            codigo = """
                @Composable
                fun Pulso() {
                    val infinite = rememberInfiniteTransition(label = "pulso")
                    val scale by infinite.animateFloat(
                        initialValue = 0.9f,
                        targetValue = 1.1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000, easing = LinearEasing),
                            repeatMode = RepeatMode.Reverse
                        ), label = "scale"
                    )
                    Box(Modifier.scale(scale)) { /* contenido */ }
                }
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Ejemplo: Crossfade", style = MaterialTheme.typography.titleSmall)
        CajaCodigo(
            codigo = """
                @Composable
                fun EstadoCrossfade() {
                    var first by remember { mutableStateOf(true) }
                    Crossfade(targetState = first, label = "cf") { isFirst ->
                        if (isFirst) { /* A */ } else { /* B */ }
                    }
                }
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text("Consejos", style = MaterialTheme.typography.titleMedium)
        CajaCodigo(
            codigo = """
                • Prefiere tween para timings lineales y spring para física natural.
                • Mantén las animaciones derivadas del estado; evita manejar frames manualmente.
                • Usa Transition/AnimationSpec compartidas para consistencia.
                • Mide performance en dispositivos reales si animas muchos elementos.
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SeccionTweenYSpring() {
    SectionBox(title = "Tween vs Spring: ¿qué son y cuándo usarlos?") {
        Text("tween: animación basada en tiempo (temporal)", style = MaterialTheme.typography.titleSmall,
            color = Color.DarkGray)
        Text(
            text = "Interpola del valor A al B en un tiempo fijo. Ideal para timings predecibles (lineales o con curvas de easing).",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(8.dp))
        CajaCodigo(
            codigo = """
                // Escala con tween lineal (300 ms)
                val scale by animateFloatAsState(
                    targetValue = if (activo) 1.2f else 1f,
                    animationSpec = tween(
                        durationMillis = 300,      // duración total
                        delayMillis = 0,           // retardo opcional
                        easing = LinearEasing      // lineal; también hay FastOutSlowInEasing, etc.
                    ),
                    label = "escalaTween"
                )
            """.trimIndent()
        )

        Spacer(Modifier.height(12.dp))
        Text("spring: animación basada en física (muelle)", style = MaterialTheme.typography.titleSmall,
            color = Color.DarkGray)
        Text(
            text = "No tiene duración fija; converge según parámetros físicos. Ideal para transiciones naturales con rebote o suavidad.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray
        )
        Spacer(Modifier.height(8.dp))
        CajaCodigo(
            codigo = """
                // Rotación con muelle (rebote medio, muelle suave)
                val rotation by animateFloatAsState(
                    targetValue = if (activo) 45f else 0f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy, // cuánto amortigua/rebota
                        stiffness = Spring.StiffnessLow                 // rigidez; menor = más suave
                    ),
                    label = "rotacionSpring"
                )
            """.trimIndent()
        )

        Spacer(Modifier.height(12.dp))
        Text("Comparación rápida", style = MaterialTheme.typography.titleSmall,
            color = Color.DarkGray)
        CajaCodigo(
            codigo = """
                // AnimatedVisibility con distinta sensación
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(animationSpec = tween(200)) + expandIn(),
                    exit  = fadeOut(animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy)) + shrinkOut()
                ) { /* contenido */ }

                // Cuándo usar cada uno
                // • tween: duraciones precisas, coreografías sincronizadas, loaders determinísticos.
                // • spring: movimientos orgánicos, tarjetas que vuelven a su lugar, microinteracciones con rebote.
            """.trimIndent()
        )
    }
}

@Composable
private fun SeccionPasoAPasoLibreriasAnimacion() {
    SectionBox(title = "Paso a paso: agregar librerías de animación") {
        Text("1) Usa el BOM de Compose (recomendado)", style = MaterialTheme.typography.titleSmall,
            color = Color.DarkGray)
        CajaCodigo(
            codigo = """
                // app/build.gradle.kts
                dependencies {
                    // Asegúrate de declarar el BOM una sola vez
                    implementation(platform(libs.androidx.compose.bom))

                    // Añade el módulo de animación
                    implementation("androidx.compose.animation:animation")
                    // Opcional: utilidades gráficas de animación (paths, etc.)
                    implementation("androidx.compose.animation:animation-graphics")
                }
            """.trimIndent()
        )

        Spacer(Modifier.height(12.dp))

        Text("3) (Opcional) Declara alias en libs.versions.toml", style = MaterialTheme.typography.titleSmall,
            color = Color.DarkGray)
        CajaCodigo(
            codigo = """
                # gradle/libs.versions.toml
                [libraries]
                androidx-compose-animation = { group = "androidx.compose.animation", name = "animation" }
                androidx-compose-animation-graphics = { group = "androidx.compose.animation", name = "animation-graphics" }
            """.trimIndent()
        )

        Spacer(Modifier.height(12.dp))
        Text("4) Sincroniza el proyecto e importa las Librerias", style = MaterialTheme.typography.titleSmall,
            color = Color.DarkGray)
        CajaCodigo(
            codigo = """
                import androidx.compose.animation.AnimatedVisibility
                import androidx.compose.animation.Crossfade
                import androidx.compose.animation.core.animateFloatAsState
                import androidx.compose.animation.core.rememberInfiniteTransition
                import androidx.compose.animation.core.tween
            """.trimIndent()
        )

        Spacer(Modifier.height(8.dp))
        Text(
            text = "Con esto ya puedes usar AnimatedVisibility, Crossfade, animate*AsState, rememberInfiniteTransition, etc.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray
        )
    }
}

@Composable
private fun CajaCodigo(codigo: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1E1E1E))
            .padding(12.dp)
    ) {
        Text(
            text = codigo,
            style = TextStyle(
                color = Color(0xFFE0E0E0),
                fontSize = 14.sp,
                lineHeight = 16.sp
            )
        )
    }
}
