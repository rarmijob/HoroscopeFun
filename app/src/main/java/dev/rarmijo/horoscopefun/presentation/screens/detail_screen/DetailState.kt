package dev.rarmijo.horoscopefun.presentation.screens.detail_screen

import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo
import dev.rarmijo.horoscopefun.domain.models.HoroscopeSign

data class DetailState(
    val isLoading: Boolean = true,
    val title: String = "",
    val horoscopeInfo: HoroscopeInfo? = null,
    val description: String = "",
)