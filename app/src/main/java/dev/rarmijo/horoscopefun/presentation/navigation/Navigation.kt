package dev.rarmijo.horoscopefun.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.rarmijo.horoscopefun.presentation.screens.horoscope_screen.HoroscopeScreen
import dev.rarmijo.horoscopefun.presentation.screens.horoscope_screen.HoroscopeViewModel

@Composable
fun Navigation(modifier: Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavItem.Horoscope.route) {
        composable(NavItem.Horoscope) {
            val vm: HoroscopeViewModel = hiltViewModel()
            val state = vm.state
            HoroscopeScreen(state = state, modifier = modifier)
        }
    }

}



private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}
