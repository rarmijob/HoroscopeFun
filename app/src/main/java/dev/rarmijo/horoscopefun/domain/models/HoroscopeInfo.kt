package dev.rarmijo.horoscopefun.domain.models

import dev.rarmijo.horoscopefun.R

sealed class HoroscopeInfo(val sign: HoroscopeSign, val name: Int, val icon: Int, val img: Int) {
    data object Aries : HoroscopeInfo(
        sign = HoroscopeSign.Aries,
        name = R.string.aries,
        icon = R.drawable.aries,
        img = R.drawable.detail_aries
    )

    data object Taurus : HoroscopeInfo(
        sign = HoroscopeSign.Taurus,
        name = R.string.taurus,
        icon = R.drawable.tauro,
        img = R.drawable.detail_taurus
    )

    data object Gemini : HoroscopeInfo(
        sign = HoroscopeSign.Gemini,
        name = R.string.gemini,
        icon = R.drawable.geminis,
        img = R.drawable.detail_gemini
    )

    data object Cancer : HoroscopeInfo(
        sign = HoroscopeSign.Cancer,
        name = R.string.cancer,
        icon = R.drawable.cancer,
        img = R.drawable.detail_cancer
    )

    data object Leo : HoroscopeInfo(
        sign = HoroscopeSign.Leo,
        name = R.string.leo,
        icon = R.drawable.leo,
        img = R.drawable.detail_leo
    )

    data object Virgo : HoroscopeInfo(
        sign = HoroscopeSign.Virgo,
        name = R.string.virgo,
        icon = R.drawable.virgo,
        img = R.drawable.detail_virgo
    )

    data object Libra : HoroscopeInfo(
        sign = HoroscopeSign.Libra,
        name = R.string.libra,
        icon = R.drawable.libra,
        img = R.drawable.detail_libra
    )

    data object Scorpio : HoroscopeInfo(
        sign = HoroscopeSign.Scorpio,
        name = R.string.scorpio,
        icon = R.drawable.escorpio,
        img = R.drawable.detail_scorpio
    )

    data object Sagittarius : HoroscopeInfo(
        sign = HoroscopeSign.Sagittarius,
        name = R.string.sagittarius,
        icon = R.drawable.sagitario,
        img = R.drawable.detail_sagittarius
    )

    data object Capricorn : HoroscopeInfo(
        sign = HoroscopeSign.Capricorn,
        name = R.string.capricorn,
        icon = R.drawable.capricornio,
        img = R.drawable.detail_capricorn
    )

    data object Aquarius : HoroscopeInfo(
        sign = HoroscopeSign.Aquarius,
        name = R.string.aquarius,
        icon = R.drawable.aquario,
        img = R.drawable.detail_aquarius
    )

    data object Pisces : HoroscopeInfo(
        sign = HoroscopeSign.Pisces,
        name = R.string.pisces,
        icon = R.drawable.piscis,
        img = R.drawable.detail_pisces
    )

    companion object {
        fun getHoroscopeInfo(sign: HoroscopeSign): HoroscopeInfo {
            return when (sign) {
                HoroscopeSign.Aries -> Aries
                HoroscopeSign.Taurus -> Taurus
                HoroscopeSign.Gemini -> Gemini
                HoroscopeSign.Cancer -> Cancer
                HoroscopeSign.Leo -> Leo
                HoroscopeSign.Virgo -> Virgo
                HoroscopeSign.Libra -> Libra
                HoroscopeSign.Scorpio -> Scorpio
                HoroscopeSign.Sagittarius -> Sagittarius
                HoroscopeSign.Capricorn -> Capricorn
                HoroscopeSign.Aquarius -> Aquarius
                HoroscopeSign.Pisces -> Pisces
            }
        }
    }
}

