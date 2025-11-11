package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintiocho

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Composable
fun LeccionVeintiochoScreen(
    navController: NavController,
    viewModel: LeccionVeintiochoViewModel = viewModel()
) {
    val planets by viewModel.planets.collectAsState()

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
                text = "Lección 28: Listas con LazyColumn",
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
                    painter = painterResource(R.drawable.list_kodee),
                    modifier = Modifier.height(160.dp),
                    contentDescription = "QR Code",
                )
            }

            SectionBox(title = "1. ¿Por qué usar LazyColumn?") {
                Text(
                    """Cuando necesitas mostrar una gran cantidad de elementos, usar un `Column` con un bucle `forEach` es ineficiente, ya que renderiza todos los elementos de la lista a la vez, incluso los que no son visibles en pantalla. Esto puede causar problemas de rendimiento.\n\n`LazyColumn` es la solución de Compose para este problema. Solo compone y renderiza los elementos que son actualmente visibles, reciclándolos a medida que el usuario se desplaza. Es el equivalente de `RecyclerView` en el sistema de Vistas clásico."""
                )
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "2. Implementación de LazyColumn") {
                CajaCodigo(
                    codigo = """
                        val miLista = listOf("Item 1", "Item 2", "Item 3", ...)

                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(miLista) { item ->
                                // Aquí defines cómo se ve cada elemento
                                Text(
                                    text = item,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                            }
                        }
                    """.trimIndent()
                )
            }

            Spacer(Modifier.height(16.dp))

            SectionBox(title = "3. Ejemplo: Lista de Planetas") {
                // Usamos un Box con altura fija para que la LazyColumn sea scrollable
                // dentro de la Column principal, que también es scrollable.
                Box(modifier = Modifier.height(300.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(planets) { planet ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(planet.name, style = MaterialTheme.typography.titleMedium)
                                    Text(planet.description, style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
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
