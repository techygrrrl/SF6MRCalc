package stream.techygrrrl.sf6mrcalc.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import stream.techygrrrl.sf6mrcalc.R
import stream.techygrrrl.sf6mrcalc.ui.PreviewTheme
import stream.techygrrrl.sf6mrcalc.ui.theme.appThemeState

@Composable
fun MatchResultsTable(
    mr: Int,
    win: Int,
    lose: Int,
    modifier: Modifier = Modifier
) {
    val appTheme by appThemeState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // MR display
        Text(
            text = stringResource(R.string.mr_display, mr),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(
                    vertical = 6.dp,
                )
        )

        // Win row
        Row(
            modifier = Modifier
                .padding(
                    vertical = 6.dp,
                )
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.result_label_win)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End,
            ) {
                // Win
                Text(
                    text = stringResource(R.string.mr_display_win, win),
                    color = appTheme.colorPalette.mrWin,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = stringResource(R.string.mr_display, mr + win),
                    color = appTheme.colorPalette.mrWin,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(
                            vertical = 6.dp,
                        )
                )
            }
        }

        // Lose row
        Row(
            modifier = Modifier
                .padding(
                    vertical = 6.dp,
                )
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = stringResource(R.string.result_label_lose)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End,
            ) {
                // Lose
                Text(
                    text = stringResource(R.string.mr_display_lose, lose),
                    color = appTheme.colorPalette.mrLose,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = stringResource(R.string.mr_display, mr - lose),
                    color = appTheme.colorPalette.mrLose,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(
                            vertical = 6.dp,
                        )
                )
            }
        }

    }

}

private data class ResultsConfig(
    val mr: Int,
    val win: Int,
    val lose: Int,
)

private class MatchResultsTablePreviewParameterConfig : PreviewParameterProvider<ResultsConfig> {
    override val values: Sequence<ResultsConfig>
        get() = sequenceOf(
            ResultsConfig(
                mr = 2200,
                win = 1,
                lose = 15,
            ),

            ResultsConfig(
                mr = 1818,
                win = 13,
                lose = 3,
            ),
            ResultsConfig(
                mr = 1350,
                win = 13,
                lose = 3,
            ),
        )
}

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(MatchResultsTablePreviewParameterConfig::class) config: ResultsConfig,
) {
    PreviewTheme {
        MatchResultsTable(
            mr = config.mr,
            win = config.win,
            lose = config.lose,
        )
    }
}
