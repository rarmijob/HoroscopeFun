package dev.rarmijo.horoscopefun.presentation.screens.horoscope_screen

import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo

data class HoroscopeState(
    val horoscopeInfoList: List<HoroscopeInfo> = emptyList(),
    val isLoading: Boolean = false
)
