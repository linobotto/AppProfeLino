package cl.linobotto.myfirstkotlinapp.view.home.lecciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cl.linobotto.myfirstkotlinapp.R
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Preview(showBackground = true)
@Composable
private fun LeccionDiezPreview() {
    LeccionDiezScreen(navController = rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeccionDiezScreen(
    navController: NavController,
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Lección 10: Scaffold en Jetpack Compose"
            )
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.kodee_waving),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))

            IntroScaffold()
            Spacer(modifier = Modifier.height(16.dp))

            CodigoScaffoldBasico()
            Spacer(modifier = Modifier.height(16.dp))

            Ventajas()

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(modifier = Modifier.fillMaxWidth(), onClick = { navController.navigate(Home) }) {
                    Text(text = "Volver al inicio")
                }
            }
        }
    }
}

@Composable
private fun IntroScaffold() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "¿Qué es Scaffold?"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = "Scaffold es un layout de alto nivel que te ayuda a estructurar pantallas comunes en Material Design: barra superior, barra inferior, FAB y el contenido principal. Facilita mantener una jerarquía visual coherente."
        )
    }
}

@Composable
private fun CodigoScaffoldBasico() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "¿Cómo se escribe?"
        )
        Spacer(modifier = Modifier.height(8.dp))

        val code = buildAnnotatedString {
            withStyle(SpanStyle(color = Color(0xFF80CBC4))) { append("@OptIn(ExperimentalMaterial3Api::class)\n") }
            append("@Composable\n")
            append("fun MiPantalla() {\n")
            append("    ")
            withStyle(SpanStyle(color = Color(0xFF82AAFF), fontWeight = FontWeight.Bold)) { append("Scaffold") }
            append("(\n")
            append("        ")
            withStyle(SpanStyle(color = Color(0xFFC3E88D))) { append("topBar") }
            append(" = {\n")
            append("            TopAppBar(\n")
            append("                title = { Text(\"Mi App\") }\n")
            append("            )\n")
            append("        }\n")
            append("    ) { ")
            withStyle(SpanStyle(color = Color(0xFFC3E88D))) { append("innerPadding") }
            append(" ->\n")
            append("        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {\n")
            append("            Text(\"Contenido principal\")\n")
            append("        }\n")
            append("    }\n")
            append("}\n")
        }

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF1E1E1E))
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = code,
                style = TextStyle(
                    color = Color(0xFFEEEEEE),
                    fontSize = 14.sp,
                    lineHeight = 18.sp
                )
            )
        }
    }
}

@Composable
private fun Ventajas() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "Ventajas de usar Scaffold"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(12.dp)
        ) {
            Bullet("Estructura consistente para múltiples pantallas")
            Bullet("Manejo automático de paddings para barras superior/inferior")
            Bullet("Fácil integración con Material 3 (TopAppBar, NavigationBar, FAB)")
        }
    }
}

@Composable
private fun Bullet(text: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text("• ", style = TextStyle(fontSize = 18.sp))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}
