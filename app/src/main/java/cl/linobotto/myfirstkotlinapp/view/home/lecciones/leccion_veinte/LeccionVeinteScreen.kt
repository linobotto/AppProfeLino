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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home
import coil.request.CachePolicy


@Composable
fun LeccionVeinteScreen(navController: NavController, viewModel: LeccionVeinteViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

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
                painter = painterResource(id = R.drawable.kodee_office),
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

            SectionBox(title = "Ejemplo práctico: Buscar Pikachu (PokeAPI)") {
                Text(
                    text = "Usaremos la PokeAPI pública (sin token): https://pokeapi.co/. Consultaremos el Pokémon ‘Pikachu’.",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    enabled = !uiState.cargando,
                    onClick = { viewModel.buscarPokemon() }
                ) { Text(if (uiState.cargando) "Buscando..." else "Buscar Pikachu") }

                Spacer(Modifier.height(8.dp))
                if (uiState.error != null) {
                    Text(text = "Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
                }
                if (uiState.pokemon != null) {
                    Spacer(Modifier.height(8.dp))
                    val imageUrl = uiState.pokemon?.sprites?.other?.official_artwork?.front_default
                        ?: uiState.pokemon?.sprites?.front_default
                        ?: ""
                    HeroCard(
                        name = uiState.pokemon?.name ?: "-",
                        imageUrl = imageUrl,
                        id = uiState.pokemon?.id ?: 0
                    )
                }
                if (uiState.resultado.isNotBlank()) {
                    Spacer(Modifier.height(8.dp))
                    CajaCodigo(codigo = uiState.resultado)
                }

                Spacer(Modifier.height(8.dp))
                CajaCodigo(
                    codigo = """
                        @Serializable
                        data class PokemonResponse(
                            val name: String? = null,
                            val sprites: Sprites? = null,
                            val height: Int? = null,
                            val weight: Int? = null,
                            val base_experience: Int? = null
                        )
                        @Serializable
                        data class Sprites(
                            val front_default: String? = null,
                            val other: OtherSprites? = null
                        )
                        @Serializable
                        data class OtherSprites(
                            @kotlinx.serialization.SerialName("official-artwork")
                            val official_artwork: OfficialArtwork? = null
                        )
                        @Serializable
                        data class OfficialArtwork(val front_default: String? = null)
                        
                        suspend fun buscarPikachu(client: HttpClient): PokemonResponse {
                            return client.get("https://pokeapi.co/api/v2/pokemon/pikachu").body()
                        }
                    """.trimIndent()
                )

                Spacer(Modifier.height(8.dp))
                CajaCodigo(
                    codigo = """
                        // En LeccionVeinteViewModel.kt se llama así:
                        viewModelScope.launch {
                            try {
                                val resp: PokemonResponse = client.get(url).body()
                                // ... usar 'resp' para actualizar el estado
                            } catch (e: Exception) {
                                // ... manejar error
                            }
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
                        // 3) Para APIs públicas (como PokeAPI) maneja límites de rate y backoff exponencial
                        // 4) Usa ViewModel + scope para cancelar llamadas si la pantalla se destruye
                        // 5) Valida conectividad y muestra mensajes de error amigables
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
private fun HeroCard(name: String, imageUrl: String, id: Int) {
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
                    .data("https://cdn.jsdelivr.net/gh/PokeAPI/sprites@master/sprites/pokemon/other/official-artwork/${id}.png")
                    .setHeader("User-Agent", "Mozilla/5.0 (Android 11; Mobile; rv:89.0)")
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.poke_ball),
                // Usamos un recurso distinto para error para distinguirlo del placeholder
                error = painterResource(id = R.drawable.error_placeholder)
            )
        }
    }
}
