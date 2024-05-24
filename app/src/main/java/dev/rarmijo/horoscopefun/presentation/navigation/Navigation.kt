package dev.rarmijo.horoscopefun.presentation.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import dev.rarmijo.horoscopefun.presentation.screens.palmistry_screen.PalmistryScreen
import dev.rarmijo.horoscopefun.presentation.screens.roulette_screen.RouletteScreen
import dev.rarmijo.horoscopefun.ui.theme.Black
import dev.rarmijo.horoscopefun.ui.theme.BlackerSmoke
import dev.rarmijo.horoscopefun.ui.theme.OrangeMystic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    var showBottomBar by remember { mutableStateOf(true) }
    var showTopBar by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = showTopBar,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TopAppBar(
                    title = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Horoscope Fun", color = OrangeMystic)
                        }
                    },
                    colors = TopAppBarColors(
                        containerColor = BlackerSmoke,
                        titleContentColor = OrangeMystic,
                        navigationIconContentColor = Black,
                        actionIconContentColor = Black,
                        scrolledContainerColor = Black
                    ),
                )
            }
        },

        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = fadeIn(),
                exit = fadeOut()
            ) {

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

            }
        },

        content = { it ->
            NavHost(
                navController = navController,
                startDestination = NavItem.Horoscope.route,
                modifier = Modifier.padding(it)
            ) {
                composable(NavItem.Horoscope) {
                    showBottomBar = true
                    showTopBar = true
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
                    showBottomBar = false
                    showTopBar = false
                    val vm: DetailViewModel = hiltViewModel()
                    val state = vm.state
                    DetailScreen(
                        modifier = modifier,
                        state = state,
                        navBack = {
                            navController.popBackStack()
                            //navController.navigate(NavItem.Horoscope.route)
                        }
                    )
                }

                composable(NavItem.Luck) {
                    showBottomBar = false
                    showTopBar = false
                    val vm: LuckViewModel = hiltViewModel()
                    val state = vm.state
                    LuckScreen(state = state,
                        navBack = {
                            navController.popBackStack()
                            //navController.navigate(NavItem.Roulette.route)
                        })
                }

                composable(NavItem.Roulette) {
                    showBottomBar = true
                    showTopBar = true
                    RouletteScreen(navToLuck = {
                        navController.navigate(NavItem.Luck.route)
                    })
                }


                composable(NavItem.Palmistry) {
                    showBottomBar = true
                    showTopBar = true
                    PalmistryScreen()
                }
            }
        },
    )
}


private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}
