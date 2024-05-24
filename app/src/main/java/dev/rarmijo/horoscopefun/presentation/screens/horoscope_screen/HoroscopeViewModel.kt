package dev.rarmijo.horoscopefun.presentation.screens.horoscope_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rarmijo.horoscopefun.domain.usecases.GetHoroscopeUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(
    private val getHoroscopeUseCase: GetHoroscopeUseCase
): ViewModel() {

    var state by mutableStateOf(HoroscopeState())
    private set

    init {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            delay(500)
            loadHoroscope()
        }
    }

    private fun loadHoroscope() {
        val listHoroscope = getHoroscopeUseCase()
        state = state.copy(horoscopeInfoList = listHoroscope, isLoading = false)
    }
}