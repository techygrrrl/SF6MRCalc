package stream.techygrrrl.sf6mrcalc.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import stream.techygrrrl.sf6mrcalc.ui.screens.calculatemr.CalculateMRScreen


@Composable
fun NavigationRouter(
    navHostController: NavHostController,
    startDestination: Route,
    modifier: Modifier = Modifier,
    onNavigate: (Route) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable<Route.MasterRateVersusWinLose> { backStackEntry ->
            CalculateMRScreen()
        }

        composable<Route.MasterRateReset> { backStackEntry ->
            Text("TODO")
        }
    }
}