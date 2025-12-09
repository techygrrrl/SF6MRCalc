package stream.techygrrrl.sf6mrcalc.ui

import android.app.UiModeManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import stream.techygrrrl.sf6mrcalc.ui.components.AppTopBar
import stream.techygrrrl.sf6mrcalc.ui.navigation.AppBottomBar
import stream.techygrrrl.sf6mrcalc.ui.navigation.NavigationRouter
import stream.techygrrrl.sf6mrcalc.ui.navigation.Route
import stream.techygrrrl.sf6mrcalc.ui.theme.SF6MRCalcTheme
import stream.techygrrrl.sf6mrcalc.ui.theme.appThemeState

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = MainViewModel()

        forceDarkMode()
        enableEdgeToEdge()

        setContent {
            val themeState by appThemeState()

            SF6MRCalcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = themeState.colorScheme.background,
                ) {
                    val navController = rememberNavController()

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            AppTopBar(
                                onResetPressed = {
                                    // Reset all the things
                                    // MR VS. Win/Lose
                                    mainViewModel.onPlayer1MrChange("")
                                    mainViewModel.onPlayer2MrChange("")
                                    // MR Reset
                                    mainViewModel.onCurrentMrChange("")
                                }
                            )
                        },
                        bottomBar = {
                            AppBottomBar(navController)
                        },
                    ) { innerPadding ->
                        NavigationRouter(
                            navHostController = navController,
                            startDestination = Route.MasterRateVersusWinLose,
                            viewModel = mainViewModel,
                            modifier = Modifier.padding(innerPadding)
                        ) { route ->
                            navController.navigate(route)
                        }
                    }
                }
            }
        }
    }

    private fun forceDarkMode() {
        val uiModeManager = getSystemService(UI_MODE_SERVICE) as UiModeManager
        uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES)
    }
}
