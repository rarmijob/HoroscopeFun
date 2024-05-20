package dev.rarmijo.horoscopefun.data.repository

import dev.rarmijo.horoscopefun.data.remote.PredictionApi
import dev.rarmijo.horoscopefun.data.remote.toDomain
import dev.rarmijo.horoscopefun.domain.models.HoroscopePrediction
import dev.rarmijo.horoscopefun.domain.repository.PredictionRepo
import dev.rarmijo.horoscopefun.domain.result.DataError
import dev.rarmijo.horoscopefun.domain.result.Result
import retrofit2.HttpException
import javax.inject.Inject

class PredictionRepoImpl @Inject constructor(private val predictionApi: PredictionApi) :
    PredictionRepo {
    override suspend fun getHoroscopePrediction(sign: String): Result<HoroscopePrediction, DataError.Network> =

        try {
            val prediction = predictionApi.getPrediction(sign).toDomain()
            Result.Success(prediction)
        } catch (e: HttpException) {
            when (e.code()) {
                400 -> {
                    Result.Error(DataError.Network.BadRequest)
                }
                401 -> {
                    Result.Error(DataError.Network.Unauthorized)
                }
                403 -> {
                    Result.Error(DataError.Network.Forbidden)
                }
                404 -> {
                    Result.Error(DataError.Network.NotFound)
                }
                500 -> {
                    Result.Error(DataError.Network.ServerError)
                }
                502 -> {
                    Result.Error(DataError.Network.BadGateway)
                }
                503 -> {
                    Result.Error(DataError.Network.ServiceUnavailable)
                }
                504 -> {
                    Result.Error(DataError.Network.GatewayTimeout)
                }
                else -> {
                    Result.Error(DataError.Network.Unknown)
                }
            }


        }

}