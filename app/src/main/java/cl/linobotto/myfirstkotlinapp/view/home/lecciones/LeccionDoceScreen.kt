package cl.linobotto.myfirstkotlinapp.view.home.lecciones

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import cl.linobotto.myfirstkotlinapp.view.core.navigation.Home

@Preview(showBackground = true)
@Composable
fun LeccionDocePreview() {
    LeccionDoceScreen(navController = rememberNavController())
}

@Composable
fun LeccionDoceScreen(navController: NavController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "12. MaterialTheme, Typography y ColorScheme",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(16.dp))

            IntroMaterialTheme()
            Spacer(Modifier.height(16.dp))
            EjemplosTypography()
            Spacer(Modifier.height(16.dp))
            EjemplosColorScheme()
            Spacer(Modifier.height(16.dp))
            DemoMaterialThemeComposable()
            Spacer(Modifier.height(16.dp))
            Text("Ejemplos de uso:")
            Spacer(Modifier.height(16.dp))
            CodigoReferencia()
            Spacer(Modifier.height(24.dp))
            Button(modifier = Modifier.fillMaxWidth(), onClick = { navController.navigate(Home) }) {
                Text("Volver")
            }
        }
    }
}

@Composable
private fun IntroMaterialTheme() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    ) {
        Text(
            text = "¿Que es el Matherial Theme.",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = "Es la forma en que aplicamos el diseño visual de Material Design a nuestras aplicaciones.\n" +
                    "Nos permite definir:\n" +
                    "• Paleta de colores\n" +
                    "• Tipografías (typography)\n" +
                    "• Formas (shapes)",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun EjemplosTypography() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp)
    ) {
        Text(
            text = "Typography: estilos listos para usar",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(8.dp))
        Text("Display Large", style = MaterialTheme.typography.displayLarge)
        Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
        Text("Title Large", style = MaterialTheme.typography.titleLarge)
        Text("Body Large", style = MaterialTheme.typography.bodyLarge)
        Text("Label Small", style = MaterialTheme.typography.labelSmall)

        Spacer(Modifier.height(12.dp))
        Text(
            text = "Puedes copiarlos y modificarlos:",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "TitleLarge + bold + color primario",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Composable
private fun EjemplosColorScheme() {
    val cs = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = cs.background)
            .padding(16.dp)
    ) {
        Text(
            text = "ColorScheme: paleta del tema",
            style = MaterialTheme.typography.titleMedium,
            color = cs.onBackground
        )
        Spacer(Modifier.height(8.dp))

        // Paleta básica
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ColorSwatch(cs.primary, cs.onPrimary, "primary")
            ColorSwatch(cs.secondary, cs.onSecondary, "secondary")
            ColorSwatch(cs.tertiary, cs.onTertiary, "tertiary")
            ColorSwatch(cs.error, cs.onError, "error")
        }
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ColorSwatch(cs.surface, cs.onSurface, "surface")
            ColorSwatch(cs.surfaceVariant, cs.onSurfaceVariant, "surfaceVar")
            ColorSwatch(cs.background, cs.onBackground, "background")
            ColorSwatch(cs.outline, cs.onBackground, "outline")
        }

        Spacer(Modifier.height(12.dp))
        Text(
            text = "Uso en componentes",
            style = MaterialTheme.typography.titleSmall,
            color = cs.primary
        )
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(cs.primaryContainer)
                .padding(12.dp)
        ) {
            Text(
                text = "Fondo primaryContainer y texto onPrimaryContainer",
                style = MaterialTheme.typography.bodyLarge,
                color = cs.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun ColorSwatch(bg: Color, fg: Color, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(bg)
        )
        Spacer(Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall, color = fg)
    }
}

@Composable
private fun DemoMaterialThemeComposable() {
    val base = MaterialTheme.colorScheme
    val innerScheme = base.copy(
        primary = Color(0xFF00695C),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFB2DFDB),
        onPrimaryContainer = Color(0xFF00332E)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = innerScheme.primaryContainer)
            .padding(16.dp)
    ) {
        Text(
            text = "Ejemplo de uso del composable MaterialTheme() en un subárbol",
            style = MaterialTheme.typography.titleMedium,
            color = innerScheme.onPrimaryContainer
        )
        Spacer(Modifier.height(8.dp))
        MaterialTheme(colorScheme = innerScheme, typography = MaterialTheme.typography) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(12.dp)
            ) {
                Text(
                    text = "Este bloque usa un MaterialTheme interno",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun CodigoReferencia() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFF263238))
            .padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Color(0xFF80CBC4))) { append("// Composable MaterialTheme local\n") }
                append("MaterialTheme(colorScheme = innerScheme, typography = MaterialTheme.typography) {\n" +
                        "   Box(\n" +
                        "       modifier = Modifier\n" +
                        "       .fillMaxWidth()\n" +
                        "       .clip(RoundedCornerShape(8.dp))\n" +
                        "       .background(MaterialTheme.colorScheme.primary)\n" +
                        "       .padding(12.dp)\n" +
                        "       ) {\n" +
                        "           Text(\n" +
                        "               text = \"Este bloque usa un MaterialTheme\n" +
                        "               interno\",\n" +
                        "               style = MaterialTheme.typography.bodyLarge,\n" +
                        "               color = MaterialTheme.colorScheme.onPrimary\n" +
                        "               )\n" +
                        "       }\n" +
                        "}\n")
                withStyle(SpanStyle(color = Color(0xFF80CBC4))) { append("// Acceso a Typography\n") }
                append("val titulo = MaterialTheme.typography.titleLarge\n")
                append("Text(\"Hola\", style = titulo)\n\n")
                withStyle(SpanStyle(color = Color(0xFF80CBC4))) { append("// Acceso a ColorScheme\n") }
                append("val cs = MaterialTheme.colorScheme\n")
                append("Box(\n")
                append("    modifier = Modifier\n")
                append("        .clip(RoundedCornerShape(8.dp))\n")
                append("        .background(cs.primaryContainer)\n")
                append(") {\n")
                append("    Text(\"Texto\", color = cs.onPrimaryContainer)\n")
                append("}\n")
            },
            style = TextStyle(fontSize = 14.sp, color = Color.White)
        )
    }
}
