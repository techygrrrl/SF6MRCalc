package stream.techygrrrl.sf6mrcalc.ui.screens.calculatemr

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import stream.techygrrrl.sf6mrcalc.R
import stream.techygrrrl.sf6mrcalc.ui.MainViewModel
import stream.techygrrrl.sf6mrcalc.ui.PreviewTheme
import stream.techygrrrl.sf6mrcalc.ui.components.MasterRankImage
import stream.techygrrrl.sf6mrcalc.ui.components.MatchResultsTable
import stream.techygrrrl.sf6mrcalc.ui.theme.appThemeState

@Composable
fun CalculateMRScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {
    val player1MrInput by viewModel.player1Input.collectAsStateWithLifecycle()
    val player2MrInput by viewModel.player2Input.collectAsStateWithLifecycle()

    val player1Mr by viewModel.player1Mr.collectAsStateWithLifecycle()
    val player2Mr by viewModel.player2Mr.collectAsStateWithLifecycle()

    val player1Result by viewModel.player1MrResult.collectAsStateWithLifecycle()
    val player2Result by viewModel.player2MrResult.collectAsStateWithLifecycle()

    Content(
        player1MrInput = player1MrInput,
        player2MrInput = player2MrInput,
        onPlayer1MrChange = viewModel::onPlayer1MrChange,
        onPlayer2MrChange = viewModel::onPlayer2MrChange,
        player1Mr = player1Mr,
        player1WinnableMr = player1Result.winnable,
        player1LosableMr = player1Result.losable,
        player2Mr = player2Mr,
        player2WinnableMr =  player2Result.winnable,
        player2LosableMr = player2Result.losable,
        modifier = modifier,
    )
}

@Composable
private fun Content(
    player1MrInput: String,
    player2MrInput: String,
    onPlayer1MrChange: (String) -> Unit,
    onPlayer2MrChange: (String) -> Unit,
    player1Mr: Int,
    player1WinnableMr: Int,
    player1LosableMr: Int,
    player2Mr: Int,
    player2WinnableMr: Int,
    player2LosableMr: Int,
    modifier: Modifier = Modifier,
) {
    val themeState by appThemeState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 20.dp,
                horizontal = 12.dp,
            )
            .verticalScroll(rememberScrollState()),
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            // Player 1 column
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Input

                OutlinedTextField(
                    value = player1MrInput,
                    onValueChange = { newValue ->
                        onPlayer1MrChange(newValue)
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.form_player_1_label),
                            fontSize = 14.sp,
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.form_mr_field_placeholder, 1500)
                        )
                    },
                    colors = themeState.outlinedTextFieldColors,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                    ),
                    trailingIcon = {
                        if (player1MrInput != "") {
                            IconButton(
                                onClick = {
                                    onPlayer1MrChange("")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = stringResource(R.string.form_fr_field_clear),
                                )
                            }
                        }
                    },
                    singleLine = true,
                )

                // Output

                if (player1MrInput != "" && player2MrInput != "") {
                    MasterRankImage(
                        mr = player1Mr,
                        modifier = Modifier
                            .padding(
                                vertical = 12.dp,
                            )
                            .fillMaxWidth()
                    )

                    MatchResultsTable(
                        mr = player1Mr,
                        win = player1WinnableMr,
                        lose = player1LosableMr,
                        modifier = Modifier
                            .background(
                                color = themeState.colorScheme.surface.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(12.dp),
                            )
                            .padding(
                                vertical = 8.dp,
                                horizontal = 16.dp,
                            )
                    )

                    // Win button sets the MRs to what it would be after player 1 wins
                    Button(
                        modifier = Modifier
                            .padding(
                                vertical = 8.dp,
                            ),
                        onClick = {
                            onPlayer1MrChange("${player1Mr + player1WinnableMr}")
                            onPlayer2MrChange("${player2Mr - player2LosableMr}")
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.mr_vs_button_player_win, 1),
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }


            Spacer(
                modifier = Modifier
                    .padding(6.dp)
            )


            // Player 2 column
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Input

                OutlinedTextField(
                    value = player2MrInput,
                    onValueChange = { newValue ->
                        onPlayer2MrChange(newValue)
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.form_player_2_label),
                            fontSize = 14.sp,
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.form_mr_field_placeholder, 1605)
                        )
                    },
                    colors = themeState.outlinedTextFieldColors,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                    ),
                    trailingIcon = {
                        if (player2MrInput != "") {
                            IconButton(
                                onClick = {
                                    onPlayer2MrChange("")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = stringResource(R.string.form_fr_field_clear),
                                )
                            }
                        }
                    },
                    singleLine = true,
                )

                // Output

                if (player1MrInput != "" && player2MrInput != "") {
                    MasterRankImage(
                        mr = player2Mr,
                        modifier = Modifier
                            .padding(
                                vertical = 12.dp,
                            )
                            .fillMaxWidth()
                    )

                    MatchResultsTable(
                        mr = player2Mr,
                        win = player2WinnableMr,
                        lose = player2LosableMr,
                        modifier = Modifier
                            .background(
                                color = themeState.colorScheme.surface.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(12.dp),
                            )
                            .padding(
                                vertical = 8.dp,
                                horizontal = 16.dp,
                            )
                    )

                    // Win button sets the MRs to what it would be after player 2 wins
                    Button(
                        modifier = Modifier
                            .padding(
                                vertical = 8.dp,
                            ),
                        onClick = {
                            onPlayer1MrChange("${player1Mr - player1LosableMr}")
                            onPlayer2MrChange("${player2Mr + player2WinnableMr}")
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.mr_vs_button_player_win, 2),
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PreviewTheme(
        modifier = Modifier.fillMaxSize()
    ) {
        Content(
            player1MrInput = "1469",
            player2MrInput = "1605",
            player1Mr = 1469,
            player2Mr = 1605,
            player1LosableMr = 1,
            player1WinnableMr = 1,
            player2LosableMr = 1,
            player2WinnableMr = 1,
            onPlayer1MrChange = {},
            onPlayer2MrChange = {},
        )
    }
}
