package dev.rarmijo.horoscopefun.domain.usecases

import dev.rarmijo.horoscopefun.domain.models.HoroscopePrediction
import dev.rarmijo.horoscopefun.domain.repository.PredictionRepo
import dev.rarmijo.horoscopefun.domain.result.DataError
import dev.rarmijo.horoscopefun.domain.result.Result
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(val repository: PredictionRepo) {

    suspend operator fun invoke(sign: String): Result<HoroscopePrediction, DataError.Network> =
        repository.getHoroscopePrediction(sign)

}