package dev.rarmijo.horoscopefun.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import kotlinx.coroutines.delay

fun Modifier.clickableOnce(onClick: () -> Unit): Modifier = composed {
    var enableAgain by remember { mutableStateOf(true) }

    LaunchedEffect(enableAgain) {
        if (!enableAgain) {
            delay(500L)
            enableAgain = true
        }
    }

    this.clickable {
        if (enableAgain) {
            enableAgain = false
            onClick()
        }
    }
}