package dev.rarmijo.horoscopefun.domain.repository

import dev.rarmijo.horoscopefun.domain.models.HoroscopePrediction
import dev.rarmijo.horoscopefun.domain.result.DataError
import dev.rarmijo.horoscopefun.domain.result.Result


interface PredictionRepo {

    suspend fun getHoroscopePrediction(sign: String): Result<HoroscopePrediction, DataError.Network>
}