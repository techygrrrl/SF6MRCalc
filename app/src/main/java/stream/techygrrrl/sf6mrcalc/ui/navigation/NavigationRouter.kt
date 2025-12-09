package stream.techygrrrl.sf6mrcalc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import stream.techygrrrl.sf6mrcalc.ui.MainViewModel
import stream.techygrrrl.sf6mrcalc.ui.screens.calculatemr.CalculateMRScreen
import stream.techygrrrl.sf6mrcalc.ui.screens.calculatemrreset.CalculateMRResetScreen


@Composable
fun NavigationRouter(
    navHostController: NavHostController,
    startDestination: Route,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
    onNavigate: (Route) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable<Route.MasterRateVersusWinLose> { backStackEntry ->
            CalculateMRScreen(viewModel)
        }

        composable<Route.MasterRateReset> { backStackEntry ->
            CalculateMRResetScreen(viewModel)
        }
    }
}
