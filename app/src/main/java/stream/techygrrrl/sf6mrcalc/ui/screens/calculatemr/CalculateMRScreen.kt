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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import stream.techygrrrl.sf6mrcalc.R
import stream.techygrrrl.sf6mrcalc.ui.PreviewTheme
import stream.techygrrrl.sf6mrcalc.ui.components.MasterRankImage
import stream.techygrrrl.sf6mrcalc.ui.components.MatchResultsTable
import stream.techygrrrl.sf6mrcalc.ui.theme.appThemeState
import stream.techygrrrl.sf6mrcalc.utils.SF6Utils

@Composable
fun CalculateMRScreen(
    modifier: Modifier = Modifier,
) {
    var player1MrInput by rememberSaveable { mutableStateOf("") }
    var player2MrInput by rememberSaveable { mutableStateOf("") }

    val player1Mr = player1MrInput.filter { it.isDigit() }.toIntOrNull() ?: 0
    val player2Mr = player2MrInput.filter { it.isDigit() }.toIntOrNull() ?: 0

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
                    .weight(1f)
            ) {
                // Input

                OutlinedTextField(
                    value = player1MrInput,
                    onValueChange = { newValue ->
                        player1MrInput = newValue
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
                                    player1MrInput = ""
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
                        win = SF6Utils.calculateWinnableMr(player1Mr, player2Mr),
                        lose = SF6Utils.calculateLosableMr(player1Mr, player2Mr),
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
                }
            }


            Spacer(
                modifier = Modifier
                    .padding(6.dp)
            )


            // Player 2 column
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                // Input

                OutlinedTextField(
                    value = player2MrInput,
                    onValueChange = { newValue ->
                        player2MrInput = newValue
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
                                    player2MrInput = ""
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
                        win = SF6Utils.calculateWinnableMr(player2Mr, player1Mr),
                        lose = SF6Utils.calculateLosableMr(player2Mr, player1Mr),
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
        CalculateMRScreen()
    }
}
