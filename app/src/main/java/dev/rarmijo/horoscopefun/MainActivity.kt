package dev.rarmijo.horoscopefun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import dev.rarmijo.horoscopefun.ui.theme.HoroscopeFunTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.rarmijo.horoscopefun.presentation.navigation.Navigation


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HoroscopeFunTheme {
                Navigation(Modifier.background(MaterialTheme.colorScheme.background))
                }
            }
        }
}
