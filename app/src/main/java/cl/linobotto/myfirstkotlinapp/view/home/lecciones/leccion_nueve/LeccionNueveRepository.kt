package cl.linobotto.myfirstkotlinapp.view.home.lecciones.leccion_nueve

class DemoRepository {
    fun construirSaludo(nombre: String): String = if (nombre.isBlank()) "Hola 👋" else "Hola, $nombre 👋"
}