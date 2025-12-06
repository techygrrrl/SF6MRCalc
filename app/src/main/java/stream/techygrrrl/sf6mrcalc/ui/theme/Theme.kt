package stream.techygrrrl.sf6mrcalc.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

private object AppColors {
    val darkPurple = Color(0xFF08082E)
    val mediumPurple = Color(0xFF240774)
    val lightPurple = Color(0xFFA83EC0)
    val white = Color(0xFFFFFFFF)
    val lightGrey = Color(0xFFAFAFAF)
    val cmykPink = Color(0xFFEE13BF)
    val cmykPurple = Color(0xFF7613EF)
    val cmykYellow = Color(0xFFEBEF18)
    val cmykBlue = Color(0xFF18AEEF)
    val mrLose = Color(0xFFD29CA8)
    val mrWin = Color(0xFFB6BFF9)
}

data class ExtendedColorPalette(
    val textColor: Color,
    val textColorMuted: Color,
    val mrLose: Color,
    val mrWin: Color,
)

val AppExtendedColorPalette = ExtendedColorPalette(
    textColor = AppColors.white,
    textColorMuted = AppColors.lightGrey,
    mrWin = AppColors.mrWin,
    mrLose = AppColors.mrLose,
)

private val SF6ColorScheme = darkColorScheme(
    background = AppColors.darkPurple,
    surface = AppColors.mediumPurple,
    primary = AppColors.cmykPink,
    secondary = AppColors.lightPurple,
    onBackground = AppColors.white,
    onSurface = AppColors.white,
    onSurfaceVariant = AppColors.white,
    onPrimary = AppColors.white,
    onSecondary = AppColors.white,
    onSecondaryContainer = AppColors.white,
    error = AppColors.mrLose,
    errorContainer = AppColors.mrLose,
    onErrorContainer = AppColors.darkPurple,
    onError = AppColors.darkPurple,
    tertiary = AppColors.lightPurple,
    onTertiary = AppColors.white,
    /*
    primaryContainer = AppColors.cmykPink,
    onPrimaryContainer = AppColors.white,
    inversePrimary = TODO(),
    secondaryContainer = TODO(),
    tertiaryContainer = TODO(),
    onTertiaryContainer = TODO(),
    surfaceVariant = TODO(),
    surfaceTint = TODO(),
    inverseSurface = TODO(),
    inverseOnSurface = TODO(),
    outline = TODO(),
    outlineVariant = TODO(),
    scrim = TODO(),
    surfaceBright = TODO(),
    surfaceContainer = TODO(),
    surfaceContainerHigh = TODO(),
    surfaceContainerHighest = TODO(),
    surfaceContainerLow = TODO(),
    surfaceContainerLowest = TODO(),
    surfaceDim = TODO(),
    */
)

data class ThemeState(
    val colorScheme: ColorScheme,
    val colorPalette: ExtendedColorPalette,
)

@Composable
fun appThemeState(): State<ThemeState> {
    return remember {
        val colorScheme = SF6ColorScheme

        mutableStateOf(
            ThemeState(
                colorScheme = colorScheme,
                colorPalette = AppExtendedColorPalette,
            )
        )
    }
}

@Composable
fun SF6MRCalcTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = SF6ColorScheme,
        typography = AppTypography,
        content = content
    )
}
