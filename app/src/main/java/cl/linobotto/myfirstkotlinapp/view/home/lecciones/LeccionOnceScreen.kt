package cl.linobotto.myfirstkotlinapp.view.home.lecciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
private fun LeccionOncePreview() {
    LeccionOnceScreen(navController = rememberNavController())
}

@Composable
fun LeccionOnceScreen(
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
                text = "Lección 11: Image e Icon en Jetpack Compose"
            )
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.kodee_greeting),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))

            IntroImageIcon()
            Spacer(modifier = Modifier.height(16.dp))

            EjemplosImage()
            Spacer(modifier = Modifier.height(16.dp))

            EjemplosIcon()

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
private fun IntroImageIcon() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "¿Qué son Image e Icon?"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = "Image muestra imágenes de tus recursos o de otros orígenes. Icon es una versión optimizada para símbolos gráficos pequeños (generalmente monocromáticos). Ambas aceptan un painter (por ejemplo painterResource) y soportan modifiers para tamaño, forma y más."
        )
    }
}

@Composable
private fun EjemplosImage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "Usando Image"
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Código (renderizado como texto coloreado)
        val code = buildAnnotatedString {
            append("@Composable\n")
            append("fun Avatar() {\n")
            append("    ")
            withStyle(SpanStyle(color = Color(0xFF82AAFF), fontWeight = FontWeight.Bold)) { append("Image") }
            append("(\n")
            append("        painter = painterResource(R.drawable.kodee_winter),\n")
            append("        contentDescription = \"Avatar\",\n")
            append("        modifier = Modifier\n")
            append("            .size(96.dp)\n")
            append("            .clip(CircleShape),\n")
            append("        contentScale = ContentScale.Crop\n")
            append("    )\n")
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

        Spacer(modifier = Modifier.height(12.dp))

        // Demostración real
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.kodee_winter),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.kodee_jumping),
                    contentDescription = "Fit",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }
        }
    }
}

@Composable
private fun EjemplosIcon() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "Usando Icon"
        )
        Spacer(modifier = Modifier.height(8.dp))

        val code = buildAnnotatedString {
            append("@Composable\n")
            append("fun MiIcono() {\n")
            append("    ")
            withStyle(SpanStyle(color = Color(0xFF82AAFF), fontWeight = FontWeight.Bold)) { append("Icon") }
            append("(\n")
            append("        painter = painterResource(R.drawable.ic_launcher_foreground),\n")
            append("        contentDescription = \"Logo\",\n")
            append("        modifier = Modifier.size(40.dp),\n")
            append("        tint = MaterialTheme.colorScheme.primary\n")
            append("    )\n")
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

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Logo app",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Icon(
                painter = painterResource(R.drawable.kodee_regular),
                contentDescription = "Icono personalizado",
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified // sin tinte para mantener colores originales
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = "Icon aplica un tinte por defecto en algunos temas. Usa tint = Color.Unspecified para mantener los colores originales del recurso."
        )
    }
}
