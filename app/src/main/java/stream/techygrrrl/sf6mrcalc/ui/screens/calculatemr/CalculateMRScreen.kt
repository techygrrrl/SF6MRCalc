package stream.techygrrrl.sf6mrcalc.ui.screens.calculatemr

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import stream.techygrrrl.sf6mrcalc.R
import stream.techygrrrl.sf6mrcalc.ui.theme.SF6MRCalcTheme
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
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        OutlinedTextField(
            value = player1MrInput,
            onValueChange = { newValue ->
                player1MrInput = newValue
            },
            label = {
                Text(stringResource(R.string.form_player_1_label))
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.form_mr_field_placeholder)
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

        Spacer(
            modifier = Modifier
                .padding(8.dp)
        )


        OutlinedTextField(
            value = player2MrInput,
            onValueChange = { newValue ->
                player2MrInput = newValue
            },
            label = {
                Text(stringResource(R.string.form_player_2_label))
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.form_mr_field_placeholder)
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

        Spacer(
            modifier = Modifier
                .padding(8.dp)
        )

        if (player1MrInput != "" && player2MrInput != "") {
            Text(
                text = stringResource(
                    id = R.string.result_player,
                    1,
                    SF6Utils.calculateWinnableMr(player1Mr, player2Mr),
                    SF6Utils.calculateLosableMr(player1Mr, player2Mr),
                )
            )
            Text(
                text = stringResource(
                    id = R.string.result_player,
                    2,
                    SF6Utils.calculateWinnableMr(player2Mr, player1Mr),
                    SF6Utils.calculateLosableMr(player2Mr, player1Mr),
                )
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SF6MRCalcTheme {
        CalculateMRScreen()
    }
}
