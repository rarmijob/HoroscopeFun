package dev.rarmijo.horoscopefun.presentation.screens.luck_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class LuckViewModel@Inject constructor(
    private val randomCardProvider: RandomCardProvider
): ViewModel() {

    var state by mutableStateOf(LuckState())
    private set

    init {
        showPrediction()
    }

    private fun showPrediction() {
        val card = randomCardProvider.getRandomCard()
        state = state.copy(
            cardTitle = card?.name ?: 0,
            cardDescription = card?.text ?: 0,
            cardImage = card?.image ?: 0,
        )

    }

}