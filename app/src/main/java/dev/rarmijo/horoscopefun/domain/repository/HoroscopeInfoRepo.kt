package dev.rarmijo.horoscopefun.domain.repository

import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo

interface HoroscopeInfoRepo {
    fun getHoroscope(): List<HoroscopeInfo>
}