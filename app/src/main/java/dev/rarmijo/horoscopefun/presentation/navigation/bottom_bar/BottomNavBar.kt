package dev.rarmijo.horoscopefun.presentation.navigation.bottom_bar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun BottomNavBar(
    currentRoute: String?,
    navAction: (route: String) -> Unit
) {

    val bottomNavItems = listOf(
        BottomNavItem.Horoscope
    )


    NavigationBar {
        bottomNavItems.forEach {
            val title = stringResource(it.title)
            NavigationBarItem(
                label = {
                    Text(text = title, style = MaterialTheme.typography.labelSmall)
                },
                icon = {
                    if (it.route == currentRoute)
                        Icon(imageVector = it.iconSelected, contentDescription = title)
                    else
                        Icon(imageVector = it.iconUnselected, contentDescription = title)
                },
                selected = it.route == currentRoute,
                // selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,

                onClick = {
                    navAction(it.route)
                },
                // Control all the colors of the icon
                colors = NavigationBarItemDefaults.colors()
            )
        }


    }
}




