package dev.rarmijo.horoscopefun.domain.usecases

import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo
import dev.rarmijo.horoscopefun.domain.repository.HoroscopeInfoRepo
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val repository: HoroscopeInfoRepo) {
    operator fun invoke(): List<HoroscopeInfo> = repository.getHoroscope()

}