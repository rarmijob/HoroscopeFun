package dev.rarmijo.horoscopefun.presentation.screens.detail_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo
import dev.rarmijo.horoscopefun.domain.models.HoroscopeSign
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle, ) : ViewModel() {

    var state by mutableStateOf(DetailState())
    private set

    init {
        savedStateHandle.get<String>("horoscopeSignName")?.let {
            val sign = HoroscopeSign.valueOf(it)
            val horoscopeInfo = HoroscopeInfo.getHoroscopeInfo(sign)
            state = state.copy(
                horoscopeInfo = horoscopeInfo
            )
        }
    }




}