package dev.rarmijo.horoscopefun.data.local.providers


import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo
import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo.*
import javax.inject.Inject

class HoroscopeProvider @Inject constructor() {
    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            Aries,
            Taurus,
            Gemini,
            Cancer,
            Leo,
            Virgo,
            Libra,
            Scorpio,
            Sagittarius,
            Capricorn,
            Aquarius,
            Pisces
        )
    }
}