package stream.techygrrrl.sf6mrcalc.ui.navigation

import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.serialization.Serializable
import stream.techygrrrl.sf6mrcalc.R

@Keep @Serializable
sealed interface Route {
    val routeName: String? get() = javaClass.simpleName

    @Keep @Serializable
    sealed interface TabRoute : Route {
        @get:StringRes val labelRes: Int
        val icon: @Composable () -> Unit
    }

    @Keep @Serializable
    data object MasterRateVersusWinLose : TabRoute {
        override val labelRes: Int get() = R.string.route_mr_vs

        override val icon: @Composable () -> Unit = {
            Icon(
                Icons.Outlined.PlayArrow,
                contentDescription = stringResource(labelRes),
            )
        }
    }

    @Keep @Serializable
    data object MasterRateReset : TabRoute {
        override val labelRes: Int get() = R.string.route_mr_reset

        override val icon: @Composable () -> Unit = {
            Icon(
                Icons.Outlined.Delete,
                contentDescription = stringResource(labelRes),
            )
        }
    }
}
