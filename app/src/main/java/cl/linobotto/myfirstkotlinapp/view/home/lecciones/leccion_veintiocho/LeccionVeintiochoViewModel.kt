package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_veintiocho

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Planet(val name: String, val description: String)

class LeccionVeintiochoViewModel : ViewModel() {

    private val _planets = MutableStateFlow<List<Planet>>(emptyList())
    val planets = _planets.asStateFlow()

    init {
        _planets.value = listOf(
            Planet("Mercurio", "El planeta más cercano al Sol."),
            Planet("Venus", "Similar en tamaño a la Tierra, pero con una atmósfera tóxica."),
            Planet("Tierra", "Nuestro hogar."),
            Planet("Marte", "El planeta rojo, con un gran interés para la exploración."),
            Planet("Júpiter", "El gigante gaseoso, el planeta más grande del sistema solar."),
            Planet("Saturno", "Famoso por sus impresionantes anillos."),
            Planet("Urano", "Un gigante de hielo que rota de lado."),
            Planet("Neptuno", "El planeta más lejano del Sol, oscuro y frío.")
        )
    }
}