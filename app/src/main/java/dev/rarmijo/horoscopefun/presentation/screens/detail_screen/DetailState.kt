package dev.rarmijo.horoscopefun.presentation.screens.detail_screen

import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo

data class DetailState(
    val title: String = "",
    val horoscopeInfo: HoroscopeInfo? = null,
    val description: String = "",
    val isLoading: Boolean = true,
    val showError: Boolean = false,
    val errorMsg: String = ""
)