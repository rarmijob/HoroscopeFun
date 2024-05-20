package dev.rarmijo.horoscopefun.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface PredictionApi {
    @GET("/{sign}")
    suspend fun getPrediction(@Path("sign") sign:String):PredictionDto
}