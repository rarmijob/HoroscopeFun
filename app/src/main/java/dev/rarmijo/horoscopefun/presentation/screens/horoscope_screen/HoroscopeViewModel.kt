package dev.rarmijo.horoscopefun.presentation.screens.horoscope_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rarmijo.horoscopefun.domain.repository.HoroscopeInfoRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(
    private val repository: HoroscopeInfoRepo
): ViewModel() {

    var state by mutableStateOf(HoroscopeState())
    private set

    init {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            delay(2000)
            loadHoroscope()
        }
    }

    private fun loadHoroscope() {
        val listHoroscope = repository.getHoroscope()
        state = state.copy(horoscopeInfoList = listHoroscope, isLoading = false)
    }
}