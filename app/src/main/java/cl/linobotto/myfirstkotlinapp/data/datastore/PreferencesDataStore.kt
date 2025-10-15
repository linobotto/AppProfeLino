package cl.linobotto.myfirstkotlinapp.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 1) Instancia de DataStore a nivel de archivo
val Context.dataStore by preferencesDataStore(name = "ajustes")

// 2) Claves de preferencias
private object PrefKeys {
    val TEMA_OSCURO: Preferences.Key<Boolean> = booleanPreferencesKey("tema_oscuro")
    val USERNAME: Preferences.Key<String> = stringPreferencesKey("username")
}

// 3) Repositorio que expone Flows (lectura) y funciones suspend (escritura)
class PreferencesRepository(private val context: Context) {

    // Flows para leer
    val temaOscuroFlow: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[PrefKeys.TEMA_OSCURO] ?: false }

    val usernameFlow: Flow<String?> = context.dataStore.data
        .map { prefs -> prefs[PrefKeys.USERNAME] }

    // Funciones suspend para escribir
    suspend fun setTemaOscuro(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[PrefKeys.TEMA_OSCURO] = enabled
        }
    }

    suspend fun setUsername(username: String) {
        context.dataStore.edit { prefs ->
            prefs[PrefKeys.USERNAME] = username
        }
    }

    suspend fun clearUsername() {
        context.dataStore.edit { prefs ->
            prefs.remove(PrefKeys.USERNAME)
        }
    }
}
