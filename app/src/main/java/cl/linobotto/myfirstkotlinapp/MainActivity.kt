package cl.linobotto.myfirstkotlinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import cl.linobotto.myfirstkotlinapp.ui.theme.AppTheme
import cl.linobotto.myfirstkotlinapp.view.core.navigation.NavigationWrapper

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme() {
                NavigationWrapper()
            }
        }
    }
}
