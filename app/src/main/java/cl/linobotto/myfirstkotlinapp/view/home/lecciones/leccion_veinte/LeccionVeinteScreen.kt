package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veinte

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.call.body
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class HeroSearchResponse(
    val response: String? = null,
    val results: List<HeroResult>? = null
)

@Serializable
data class HeroResult(
    val id: String? = null,
    val name: String? = null,
    val image: HeroImage? = null,
    val powerstats: PowerStats? = null
)

@Serializable
data class HeroImage(val url: String? = null)

@Serializable
data class PowerStats(
    val intelligence: String? = null,
    val strength: String? = null,
    val speed: String? = null,
    val durability: String? = null,
    val power: String? = null,
    val combat: String? = null
)

@Composable
fun LeccionVeinteScreen(navController: NavController) {
    var resultado by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var heroe by remember { mutableStateOf<HeroResult?>(null) }

    // Cliente Ktor básico para Android
    val client = remember {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }
            install(Logging) {
                level = LogLevel.BODY
            }
            install(io.ktor.client.plugins.HttpTimeout) {
                requestTimeoutMillis = 15000
            }
        }
    }

    val scope = rememberCoroutineScope()

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
                text = "Lección 20: Consumo de API con Ktor",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.kodee_auto_fondo_wide),
                contentDescription = null
            )
            Spacer(Modifier.height(12.dp))

            SectionBox(title = "¿Qué aprenderás?") {
                Text(
                    text = "Cómo consumir un API REST desde Android usando Ktor Client: añadir dependencias, configurar el cliente, realizar peticiones GET y parsear JSON con kotlinx.serialization.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Dependencias (Gradle)") {
                CajaCodigo(
                    codigo = """
                        // En gradle/libs.versions.toml (ya agregado por nosotros)
                        ktor = "3.0.1"
                        # Librerías
                        ktor-client-android
                        ktor-client-content-negotiation
                        ktor-serialization-kotlinx-json
                        ktor-client-logging
                        
                        // En app/build.gradle.kts
                        dependencies {
                            implementation(libs.ktor.client.android)
                            implementation(libs.ktor.client.content.negotiation)
                            implementation(libs.ktor.serialization.kotlinx.json)
                            implementation(libs.ktor.client.logging)
                        }
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Configurar Ktor Client") {
                CajaCodigo(
                    codigo = """
                        val client = HttpClient(Android) {
                            install(ContentNegotiation) {
                                json(Json { ignoreUnknownKeys = true })
                            }
                            install(Logging) { level = LogLevel.BODY }
                        }
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Ejemplo práctico: Buscar \"Batman\" (SuperHero API)") {
                Text(
                    text = "Necesitas un token gratuito de https://superheroapi.com/. Reemplaza TU_TOKEN_AQUI abajo.",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    enabled = !cargando,
                    onClick = {
                        // Llamada simple usando Ktor desde un hilo de suspensión
                        cargando = true
                        error = null
                        resultado = ""
                        heroe = null
                        scope.launch(Dispatchers.IO) {
                            try {
                                val token = "eba8c769a99b56f33f878449915e4ee3" // Reemplaza por tu token real de superheroapi.com
                                val url = "https://superheroapi.com/api/$token/search/batman"
                                val resp: HeroSearchResponse = client.get(url).body()
                                val resultadoHeroe = resp.results?.firstOrNull()
                                val nombre = resultadoHeroe?.name ?: "-"
                                val imagen = resultadoHeroe?.image?.url ?: "-"
                                val inteligencia = resultadoHeroe?.powerstats?.intelligence ?: "-"
                                val fuerza = resultadoHeroe?.powerstats?.strength ?: "-"
                                val texto = buildString {
                                    appendLine("Nombre: $nombre")
                                    appendLine("Imagen: $imagen")
                                    appendLine("Powerstats: inteligencia=$inteligencia, fuerza=$fuerza")
                                }
                                withContext(Dispatchers.Main) {
                                    heroe = resultadoHeroe
                                    resultado = texto
                                    cargando = false
                                }
                            } catch (e: Exception) {
                                withContext(Dispatchers.Main) {
                                    error = e.message
                                    cargando = false
                                }
                            }
                        }
                    }
                ) { Text(if (cargando) "Buscando..." else "Buscar Batman") }

                Spacer(Modifier.height(8.dp))
                if (error != null) {
                    Text(text = "Error: ${'$'}error", color = MaterialTheme.colorScheme.error)
                }
                if (heroe != null) {
                    Spacer(Modifier.height(8.dp))
                    HeroCard(
                        name = heroe?.name ?: "-",
                        imageUrl = heroe?.image?.url.orEmpty()
                    )
                }
                if (resultado.isNotBlank()) {
                    Spacer(Modifier.height(8.dp))
                    CajaCodigo(codigo = resultado)
                }

                Spacer(Modifier.height(8.dp))
                CajaCodigo(
                    codigo = """
                        @Serializable
                        data class HeroSearchResponse(val response: String? = null, val results: List<HeroResult>? = null)
                        @Serializable
                        data class HeroResult(val id: String? = null, val name: String? = null, val image: HeroImage? = null, val powerstats: PowerStats? = null)
                        @Serializable data class HeroImage(val url: String? = null)
                        @Serializable data class PowerStats(val intelligence: String? = null, val strength: String? = null, val speed: String? = null,
                            val durability: String? = null, val power: String? = null, val combat: String? = null)
                        
                        suspend fun buscarBatman(client: HttpClient, token: String): HeroResult? {
                            val url = "https://superheroapi.com/api/${'$'}token/search/batman"
                            val resp: HeroSearchResponse = client.get(url).body()
                            return resp.results?.firstOrNull()
                        }
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(12.dp))

            SectionBox(title = "Buenas prácticas y errores comunes") {
                CajaCodigo(
                    codigo = """
                        // 1) Nunca llames APIs en el hilo principal: usa corrutinas (Dispatchers.IO)
                        // 2) Maneja excepciones (try/catch) y estados de carga/errores en UI
                        // 3) Mantén el token fuera del código (BuildConfig, EncryptedSharedPreferences, etc.)
                        // 4) Usa ViewModel + scope para cancelar llamadas si la pantalla se destruye
                        // 5) Respeta límites de rate y maneja códigos HTTP
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

@Composable
private fun HeroCard(name: String, imageUrl: String) {
    val context = LocalContext.current
    // Si la URL viene con http, intentamos forzar https (muchos hosts lo soportan)
    val imageUrlToLoad = remember(imageUrl) {
        if (imageUrl.startsWith("http://", ignoreCase = true)) {
            imageUrl.replaceFirst("http://", "https://", ignoreCase = true)
        } else imageUrl
    }
    var loadError by remember { mutableStateOf<String?>(null) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        if (imageUrlToLoad.isBlank()) {
            // No intentamos cargar nada si está vacío
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sin imagen disponible",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            AsyncImage(
                model = coil.request.ImageRequest.Builder(context)
                    .data(imageUrlToLoad)
                    .crossfade(true)
                    .build(),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.kodee_regular),
                // Usamos un recurso distinto para error para distinguirlo del placeholder
                error = painterResource(id = R.drawable.kodee_scream),
                onError = { state ->
                    loadError = state.result.throwable.message
                },
                onSuccess = {
                    loadError = null
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
        }
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(12.dp)
        )
        Text(
            text = imageUrlToLoad,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        if (loadError != null) {
            Text(
                text = "Error cargando imagen: ${loadError}",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(12.dp)
            )
            Text(
                text = "Sugerencia: verifica conexión. Si la URL es http, habilita https o permite cleartext.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}
