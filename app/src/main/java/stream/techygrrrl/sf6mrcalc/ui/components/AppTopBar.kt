package stream.techygrrrl.sf6mrcalc.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import stream.techygrrrl.sf6mrcalc.R
import stream.techygrrrl.sf6mrcalc.ui.theme.appThemeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(modifier: Modifier = Modifier) {
    val themeState by appThemeState()

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name_long),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        navigationIcon = { null },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = themeState.colorScheme.primary,
            titleContentColor = themeState.colorScheme.onPrimary,
        ),
        modifier = modifier,
    )
}
