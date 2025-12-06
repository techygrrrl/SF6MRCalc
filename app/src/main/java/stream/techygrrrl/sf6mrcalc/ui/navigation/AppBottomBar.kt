package stream.techygrrrl.sf6mrcalc.ui.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import stream.techygrrrl.sf6mrcalc.ui.theme.appThemeState

@Composable
fun AppBottomBar(navController: NavController) {
    val haptic = LocalHapticFeedback.current
    val appTheme by appThemeState()

    val navItems = listOf<Route.TabRoute>(
        Route.MasterRateVersusWinLose,
        Route.MasterRateReset,
    )

    var selectedItem by remember { mutableStateOf<Route?>(Route.MasterRateVersusWinLose) }

    fun handleRouteClicked(route: Route) {
        selectedItem = route

        navController.navigate(route) {
            navController.graph.startDestinationRoute?.let { startRoute ->
                popUpTo(startRoute) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    LaunchedEffect(0) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            navItems.forEach { navItem ->
                val current = destination.route?.split(".")?.lastOrNull()

                if (current == navItem.routeName) {
                    selectedItem = navItem
                    return@addOnDestinationChangedListener
                }

                selectedItem = null
            }
        }
    }

    NavigationBar(
        windowInsets = NavigationBarDefaults.windowInsets,
        containerColor = appTheme.colorScheme.surface,
    ) {
        navItems.forEach { navItem ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = appTheme.colorPalette.sf6LightPurple,
                    indicatorColor = appTheme.colorPalette.sf6MediumPurple,
                ),
                selected = navItem == selectedItem,
                label = {
                    Text(
                        text = stringResource(navItem.labelRes),
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                icon = navItem.icon,
                onClick = {
                    handleRouteClicked(navItem)
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
            )
        }
    }
}
