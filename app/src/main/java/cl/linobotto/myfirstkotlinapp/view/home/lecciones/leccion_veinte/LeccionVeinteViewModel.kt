package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veinte

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// Data classes for API response (could be in a separate file)
@Serializable
data class PokemonResponse(
    val name: String? = null,
    val id: Int? = null,
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
data class OfficialArtwork(
    val front_default: String? = null
)

// UI State
data class LeccionVeinteUiState(
    val cargando: Boolean = false,
    val error: String? = null,
    val pokemon: PokemonResponse? = null,
    val resultado: String = ""
)

class LeccionVeinteViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LeccionVeinteUiState())
    val uiState: StateFlow<LeccionVeinteUiState> = _uiState.asStateFlow()

    private val client = HttpClient(Android) {
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
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
        }
    }

    fun buscarPokemon() {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null, pokemon = null, resultado = "") }
            try {
                val url = "https://pokeapi.co/api/v2/pokemon/pikachu"
                val resp: PokemonResponse = client.get(url).body()
                val nombre = resp.name ?: "-"
                val imagen = resp.sprites?.other?.official_artwork?.front_default
                    ?: resp.sprites?.front_default
                    ?: "-"
                val texto = buildString {
                    appendLine("Nombre: $nombre")
                    appendLine("Imagen: $imagen")
                    appendLine("Altura: ${resp.height ?: 0} | Peso: ${resp.weight ?: 0}")
                }
                _uiState.update {
                    it.copy(
                        cargando = false,
                        pokemon = resp,
                        resultado = texto
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        cargando = false,
                        error = e.message
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        client.close()
    }
}