package stream.techygrrrl.sf6mrcalc.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import stream.techygrrrl.sf6mrcalc.ui.theme.SF6MRCalcTheme
import stream.techygrrrl.sf6mrcalc.ui.theme.appThemeState


@Composable
fun PreviewTheme(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val themeState by appThemeState()

    SF6MRCalcTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = themeState.colorScheme.background,
        ) {
            content()
        }
    }
}
