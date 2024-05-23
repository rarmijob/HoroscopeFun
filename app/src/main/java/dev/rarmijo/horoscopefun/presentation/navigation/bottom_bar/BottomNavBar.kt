package dev.rarmijo.horoscopefun.presentation.navigation.bottom_bar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.rarmijo.horoscopefun.ui.theme.BlackerSmoke
import dev.rarmijo.horoscopefun.ui.theme.Gray
import dev.rarmijo.horoscopefun.ui.theme.OrangeMystic
import dev.rarmijo.horoscopefun.ui.theme.Transparent
import dev.rarmijo.horoscopefun.ui.theme.White

@Composable
fun BottomNavBar(
    currentRoute: String?,
    navAction: (route: String) -> Unit
) {

    val bottomNavItems = listOf(
        BottomNavItem.Horoscope,
        BottomNavItem.Luck,
        BottomNavItem.Palmistry
    )


    NavigationBar(
        containerColor = BlackerSmoke
    ) {
        bottomNavItems.forEach {
            val title = stringResource(it.title)
            NavigationBarItem(
                label = {
                    Text(text = title, style = MaterialTheme.typography.labelSmall)
                },
                icon = {

                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = title,
                        modifier = Modifier.size(24.dp)
                    )
/*

                    if (it.route == currentRoute)
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = title,
                            modifier = Modifier.size(24.dp)
                        )
                    else
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = title,
                            tint = MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(24.dp)
                        )

*/

                },
                //TODO failing with detail route, improve logic
                selected = it.route == currentRoute,
                //selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,

                onClick = {
                    navAction(it.route)
                },
                // Control all the colors of the icon
                colors = NavigationBarItemColors(
                    selectedIconColor = OrangeMystic,
                    selectedTextColor = OrangeMystic,
                    unselectedIconColor = White,
                    unselectedTextColor = White,
                    disabledIconColor = Gray,
                    disabledTextColor = Gray,
                    selectedIndicatorColor = Transparent,
                )
            )
        }


    }
}




