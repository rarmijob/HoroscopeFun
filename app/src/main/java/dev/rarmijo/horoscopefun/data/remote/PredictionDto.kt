package dev.rarmijo.horoscopefun.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.rarmijo.horoscopefun.domain.models.HoroscopePrediction


@JsonClass(generateAdapter = true)
data class PredictionDto(
    @Json(name = "date")
    val date: String,
    @Json(name = "horoscope")
    val horoscope: String,
    @Json(name = "icon")
    val icon: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "sign")
    val sign: String
)

fun PredictionDto.toDomain(): HoroscopePrediction =
    HoroscopePrediction(
        title = sign,
        description = horoscope,
        )