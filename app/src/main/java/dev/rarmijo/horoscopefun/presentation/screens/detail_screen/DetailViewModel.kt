package dev.rarmijo.horoscopefun.presentation.screens.detail_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo
import dev.rarmijo.horoscopefun.domain.models.HoroscopeSign
import dev.rarmijo.horoscopefun.domain.result.DataError
import dev.rarmijo.horoscopefun.domain.result.Result
import dev.rarmijo.horoscopefun.domain.usecases.GetPredictionUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val getPredictionUseCase: GetPredictionUseCase
) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    init {
        savedStateHandle.get<String>("horoscopeSignName")?.let {
            val sign = HoroscopeSign.valueOf(it)
            val horoscopeInfo = HoroscopeInfo.getHoroscopeInfo(sign)
            state = state.copy(horoscopeInfo = horoscopeInfo)
            getPrediction()
        }
    }


    private fun getPrediction() {

        val signName = state.horoscopeInfo?.sign?.name

        viewModelScope.launch {
            if (signName != null) {
                when (val prediction = getPredictionUseCase(signName)) {
                    is Result.Error -> {


                        val errorMsg = when (prediction.error) {
                            DataError.Network.BadRequest -> "BadRequest"
                            DataError.Network.Unauthorized -> "Unauthorized"
                            DataError.Network.Forbidden -> "Forbidden"
                            DataError.Network.NotFound -> "NotFound"
                            DataError.Network.RequestTimeout -> "RequestTimeout"
                            DataError.Network.TooManyRequests -> "TooManyRequests"
                            DataError.Network.NoInternet -> "NoInternet"
                            DataError.Network.PayloadTooLarge -> "PayloadTooLarge"
                            DataError.Network.ServerError -> "ServerError"
                            DataError.Network.Serialization -> "Serialization"
                            DataError.Network.BadGateway -> "BadGateway"
                            DataError.Network.ServiceUnavailable -> "ServiceUnavailable"
                            DataError.Network.GatewayTimeout -> "GatewayTimeout"
                            DataError.Network.Unknown -> "Unknown"
                        }

                        state = state.copy(
                            isLoading = false,
                            showError = true,
                            errorMsg = errorMsg
                        )

                        //Log.e("DetailViewModel", "getPrediction: $errorMsg")

                    }

                    is Result.Success -> {
                        state = state.copy(
                            description = prediction.data.description,
                            title = prediction.data.title,
                            isLoading = false
                        )

                    }
                }
            }

        }
    }


}