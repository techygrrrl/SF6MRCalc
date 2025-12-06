package stream.techygrrrl.sf6mrcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import stream.techygrrrl.sf6mrcalc.ui.navigation.AppBottomBar
import stream.techygrrrl.sf6mrcalc.ui.navigation.NavigationRouter
import stream.techygrrrl.sf6mrcalc.ui.navigation.Route
import stream.techygrrrl.sf6mrcalc.ui.theme.SF6MRCalcTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SF6MRCalcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
//                        topBar = {
//
//                        },
                        bottomBar = {
                            AppBottomBar(navController)
                        },
                    ) { innerPadding ->
                        NavigationRouter(
                            navHostController = navController,
                            startDestination = Route.MasterRateVersusWinLose,
                            modifier = Modifier.padding(innerPadding)
                        ) { route ->
                            navController.navigate(route)
                        }
                    }
                }
            }
        }
    }
}
