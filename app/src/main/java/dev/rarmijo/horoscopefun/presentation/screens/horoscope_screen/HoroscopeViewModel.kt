package dev.rarmijo.horoscopefun.presentation.screens.horoscope_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(): ViewModel() {

    var state by mutableStateOf(HoroscopeState())
    private set

    init {
        state = state.copy(isLoading = true)
    }
}