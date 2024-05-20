package dev.rarmijo.horoscopefun.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    private val navArgs: List<NavArgs> = emptyList()
) {

    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) {
            type = it.navType
        }
    }

    data object Horoscope: NavItem("horoscope")
    data object Detail: NavItem("Detail", listOf(NavArgs.HoroscopeSignName)) {
        fun createNavRoute(horoscopeSignName: String) = "$baseRoute/$horoscopeSignName"
    }
    data object Luck: NavItem("luck")
    data object Palmistry: NavItem("palmistry")


}

enum class NavArgs(val key: String, val navType: NavType<*>) {
    HoroscopeSignName("horoscopeSignName", NavType.StringType)
}