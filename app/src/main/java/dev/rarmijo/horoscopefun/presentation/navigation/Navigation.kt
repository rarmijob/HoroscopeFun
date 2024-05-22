package dev.rarmijo.horoscopefun.presentation.navigation

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.rarmijo.horoscopefun.presentation.navigation.bottom_bar.BottomNavBar
import dev.rarmijo.horoscopefun.presentation.screens.detail_screen.DetailScreen
import dev.rarmijo.horoscopefun.presentation.screens.detail_screen.DetailViewModel
import dev.rarmijo.horoscopefun.presentation.screens.horoscope_screen.HoroscopeScreen
import dev.rarmijo.horoscopefun.presentation.screens.horoscope_screen.HoroscopeViewModel
import dev.rarmijo.horoscopefun.presentation.screens.luck_screen.LuckScreen
import dev.rarmijo.horoscopefun.presentation.screens.luck_screen.LuckViewModel
import dev.rarmijo.horoscopefun.ui.theme.Black
import dev.rarmijo.horoscopefun.ui.theme.BlackerSmoke
import dev.rarmijo.horoscopefun.ui.theme.OrangeMystic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(modifier: Modifier) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(

        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = "Horoscope Fun", color = OrangeMystic)
                },
                colors = TopAppBarColors(
                    containerColor = BlackerSmoke,
                    titleContentColor = OrangeMystic,
                    navigationIconContentColor = Black,
                    actionIconContentColor = Black,
                    scrolledContainerColor = Black
                )


            )

        },


        bottomBar = {
            val currentRoute = navBackStackEntry?.destination?.route
            BottomNavBar(currentRoute) {
                navController.navigate(it) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }

        },
        content = {
            NavHost(
                navController = navController,
                startDestination = NavItem.Horoscope.route,
                modifier = Modifier.padding(it)
            ) {
                composable(NavItem.Horoscope) {
                    val vm: HoroscopeViewModel = hiltViewModel()
                    val state = vm.state
                    HoroscopeScreen(
                        modifier = modifier,
                        state = state,
                        navToDetail = { info ->
                            navController.navigate(NavItem.Detail.createNavRoute(info.sign.name))
                        }
                    )
                }

                composable(NavItem.Detail) {
                    val vm: DetailViewModel = hiltViewModel()
                    val state = vm.state
                    DetailScreen(
                        modifier = modifier,
                        state = state,

                        )
                }

                composable(NavItem.Luck) {

                    val vm: LuckViewModel = hiltViewModel()
                    val state = vm.state
                    LuckScreen(state = state)
                }
            }

        },
    )


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
