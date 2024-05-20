package dev.rarmijo.horoscopefun.data.repository

import dev.rarmijo.horoscopefun.data.local.providers.HoroscopeProvider
import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo
import dev.rarmijo.horoscopefun.domain.repository.HoroscopeInfoRepo
import javax.inject.Inject

class HoroscopeInfoRepoImpl @Inject constructor(
    private val provider: HoroscopeProvider
) : HoroscopeInfoRepo {
    override fun getHoroscope(): List<HoroscopeInfo> = provider.getHoroscopes()

}