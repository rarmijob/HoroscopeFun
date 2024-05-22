package dev.rarmijo.horoscopefun.presentation.navigation.bottom_bar

import dev.rarmijo.horoscopefun.R
import dev.rarmijo.horoscopefun.presentation.navigation.NavItem


sealed class BottomNavItem(
    val title: Int,
    val route: String,
    val icon: Int,

) {
    data object Horoscope :
        BottomNavItem(
            title = R.string.HoroscopeItemNav,
            route = NavItem.Horoscope.baseRoute,
            icon = R.drawable.ic_horoscope,

        )

    data object Luck :
        BottomNavItem(
            title = R.string.luckItemNav,
            route = NavItem.Luck.baseRoute,
            icon = R.drawable.ic_cards,

        )

    data object Palmistry :
        BottomNavItem(
            title = R.string.palmistryItemNav,
            route = NavItem.Palmistry.baseRoute,
            icon = R.drawable.ic_hand,

        )
}
