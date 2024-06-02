package dev.rarmijo.horoscopefun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import dev.rarmijo.horoscopefun.presentation.navigation.Navigation
import dev.rarmijo.horoscopefun.ui.theme.HoroscopeFunTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HoroscopeFunTheme {
                Navigation()
            }
        }
    }



}
