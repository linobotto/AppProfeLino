/*
package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_quince

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Switch
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home
import cl.linobotto.myfirstkotlinapp.data.datastore.PreferencesRepository

@Preview(showBackground = true)
@Composable
fun LeccionQuincePreview() {
    LeccionQuinceScreen(navController = rememberNavController())
}

@Composable
fun LeccionQuinceScreen(navController: NavController) {
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
                text = "15. DataStore: Ejemplos e instrucciones"
            )
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.kodee_waving),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))

            DemoPreferencias()

            SeccionExplicacionDataStore()

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { navController.navigate(Home) },
                modifier = Modifier.fillMaxWidth(),
            ) { Text(text = "Volver a las lecciones") }
        }
    }
}

@Composable
private fun DemoPreferencias() {
    val context = LocalContext.current
    val repo = remember(context) { PreferencesRepository(context) }
    val scope = rememberCoroutineScope()

    val temaOscuro by repo.temaOscuroFlow.collectAsState(initial = false)
    val username by repo.usernameFlow.collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFEFF3F8))
            .padding(16.dp)
    ) {
        Text(text = "Demo en vivo: Preferences DataStore", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Tema oscuro: ${'$'}temaOscuro")
        Spacer(modifier = Modifier.height(8.dp))
        Switch(
            checked = temaOscuro,
            onCheckedChange = { checked ->
                scope.launch { repo.setTemaOscuro(checked) }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Username: " + (username ?: "<sin definir>"))
        Spacer(modifier = Modifier.height(8.dp))
        RowAccionesUsuario(
            onSet = { scope.launch { repo.setUsername("Lino") } },
            onClear = { scope.launch { repo.clearUsername() } }
        )
    }
}

@Composable
private fun RowAccionesUsuario(onSet: () -> Unit, onClear: () -> Unit) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(modifier = Modifier.weight(1f), onClick = onSet) { Text("Guardar 'Lino'") }
        Button(modifier = Modifier.weight(1f), onClick = onClear) { Text("Borrar username") }
    }
}

@Composable
private fun SeccionExplicacionDataStore() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "¿Qué es DataStore?",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "DataStore es la librería moderna de Android para guardar datos de forma local, segura y reactiva. Reemplaza a SharedPreferences para preferencias y también ofrece una variante con protobuf para modelos tipados.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Cuándo usarlo", style = MaterialTheme.typography.titleSmall)
        Text(
            text = "• Preferencias simples (tema, flags, último usuario).\n• Configuraciones reactivas que cambian la UI.\n• Pequeñas entidades tipadas con Proto (no relacional).",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text("Preferencias DataStore (CRUD básico)", style = MaterialTheme.typography.titleMedium)

        CajaCodigo(
            codigo = """
                // build.gradle (app)
                // implementation("androidx.datastore:datastore-preferences:1.1.1")

                // 1) Crear instancia (a nivel de archivo)
                val Context.dataStore by preferencesDataStore(name = "ajustes")

                // 2) Definir claves
                val TEMA_OSCURO = booleanPreferencesKey("tema_oscuro")
                val USERNAME = stringPreferencesKey("username")

                // 3) Escribir
                suspend fun setTemaOscuro(context: Context, enabled: Boolean) {
                    context.dataStore.edit { prefs ->
                        prefs[TEMA_OSCURO] = enabled
                    }
                }

                // 4) Leer como Flow
                val temaOscuroFlow: Flow<Boolean> = context.dataStore.data
                    .map { prefs -> prefs[TEMA_OSCURO] ?: false }

                // 5) Borrar una clave
                suspend fun clearUsername(context: Context) {
                    context.dataStore.edit { it.remove(USERNAME) }
                }
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Uso en Compose (collectAsState)", style = MaterialTheme.typography.titleSmall)
        CajaCodigo(
            codigo = """
                @Composable
                fun PreferenciasUI(context: Context) {
                    val temaOscuro by remember(context) {
                        temaOscuroFlow // Flow<Boolean>
                    }.collectAsState(initial = false)

                    Switch(checked = temaOscuro, onCheckedChange = {
                        // lanzar coroutine para setTemaOscuro(context, it)
                    })
                }
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text("Proto DataStore (modelo tipado)", style = MaterialTheme.typography.titleMedium)
        Text(
            text = "Proto DataStore usa protobuf para definir un esquema tipado. Es ideal si quieres un objeto estructurado, pero no necesitas relaciones ni consultas complejas como en Room.",
            style = MaterialTheme.typography.bodyMedium
        )
        CajaCodigo(
            codigo = """
                // build.gradle (app)
                // implementation("androidx.datastore:datastore:1.1.1")

                // Definir .proto (ej: user_prefs.proto)
                // message UserPrefs { string nombre = 1; int32 edad = 2; }

                // Crear Serializer y la instancia de DataStore<UserPrefs>
                // Luego leer con data.map { } y escribir con updateData { }
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text("DataStore vs SharedPreferences vs Room", style = MaterialTheme.typography.titleMedium)
        CajaCodigo(
            codigo = """
                SharedPreferences
                - API síncrona; riesgo de bloquear hilo principal.
                - No es reactivo (necesitas listeners).
                - Obsoleto para nuevas implementaciones.

                DataStore (Preferences)
                - API 100% asíncrona y basada en Flow.
                - Transaccional y segura ante corrupción.
                - Key-Value; ideal para settings.

                DataStore (Proto)
                - Modelo tipado (protobuf).
                - Estructurado pero sin relaciones.

                Room (SQLite)
                - Base de datos relacional.
                - Consultas complejas (JOIN, índices, relaciones).
                - Ideal para listas, cache y datos grandes.
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text("Pasos para implementarlo en tu app", style = MaterialTheme.typography.titleMedium)
        CajaCodigo(
            codigo = """
                1) Agrega la dependencia de DataStore Preferences.
                2) Crea las keys y la instancia con preferencesDataStore.
                3) Expone Flows para leer y funciones suspend para escribir.
                4) En Compose, usa collectAsState() para reaccionar a los cambios.
                5) Si necesitas objetos tipados, evalúa Proto DataStore.
            """.trimIndent()
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
}*/
