package stream.techygrrrl.sf6mrcalc.ui.screens.calculatemrreset

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import stream.techygrrrl.sf6mrcalc.R
import stream.techygrrrl.sf6mrcalc.ui.PreviewTheme
import stream.techygrrrl.sf6mrcalc.ui.components.MasterRankImage
import stream.techygrrrl.sf6mrcalc.ui.theme.appThemeState
import stream.techygrrrl.sf6mrcalc.utils.SF6Utils

@Composable
fun CalculateMRResetScreen(
    modifier: Modifier = Modifier
) {

    var currentMrInput by rememberSaveable { mutableStateOf("") }
    val currentMr = currentMrInput.filter { it.isDigit() }.toIntOrNull() ?: 0

    val resetMrValue = SF6Utils.calculateResetMr(currentMr)

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

        // Input current MR
        OutlinedTextField(
            value = currentMrInput,
            onValueChange = { newValue ->
                currentMrInput = newValue
            },
            label = {
                Text(
                    text = stringResource(R.string.form_current_mr_label),
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
                if (currentMrInput != "") {
                    IconButton(
                        onClick = {
                            currentMrInput = ""
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
        if (currentMrInput != "") {
            Row(
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                    )
                    .fillMaxWidth()
            ) {
                // Current Phase
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(R.string.mr_reset_current_phase),
                        color = themeState.colorPalette.textColorMuted,
                    )

                    Text(
                        text = stringResource(R.string.mr_display, currentMr),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(
                                vertical = 8.dp,
                            )
                    )

                    MasterRankImage(
                        mr = currentMr,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Spacer(
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp,
                        )
                )


                // Next phase
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(R.string.mr_reset_next_phase),
                        color = themeState.colorPalette.textColorMuted,
                    )

                    Text(
                        text = stringResource(R.string.mr_display, resetMrValue),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(
                                vertical = 8.dp,
                            )
                    )

                    MasterRankImage(
                        mr = resetMrValue,
                        modifier = Modifier
                            .fillMaxWidth()
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
        CalculateMRResetScreen()
    }
}
