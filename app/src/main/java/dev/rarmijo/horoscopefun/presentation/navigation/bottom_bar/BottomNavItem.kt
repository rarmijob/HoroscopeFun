package dev.rarmijo.horoscopefun.presentation.navigation.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import dev.rarmijo.horoscopefun.R
import dev.rarmijo.horoscopefun.presentation.navigation.NavItem


sealed class BottomNavItem(
    val title: Int,
    val route: String,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector
) {
    data object Horoscope :
        BottomNavItem(
            title = R.string.HoroscopeItemNav,
            route = NavItem.Horoscope.baseRoute,
            iconSelected = Icons.Filled.Home,
            iconUnselected = Icons.Outlined.Home
        )

//    data object Report :
//        BottomNavItem(
//            title = "Detail",
//            route = NavItem.Detail.baseRoute,
//            iconSelected = Icons.Filled.Analytics,
//            iconUnselected = Icons.Outlined.Analytics
//        )
}
