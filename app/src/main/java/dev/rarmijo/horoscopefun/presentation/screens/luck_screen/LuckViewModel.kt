package dev.rarmijo.horoscopefun.presentation.screens.luck_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LuckViewModel@Inject constructor(): ViewModel() {

    var state by mutableStateOf(LuckState())
    private set







}