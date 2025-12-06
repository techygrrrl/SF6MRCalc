package stream.techygrrrl.sf6mrcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import stream.techygrrrl.sf6mrcalc.ui.screens.calculatemr.CalculateMRScreen
import stream.techygrrrl.sf6mrcalc.ui.theme.SF6MRCalcTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        enableEdgeToEdge()

        setContent {
            SF6MRCalcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculateMRScreen(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
