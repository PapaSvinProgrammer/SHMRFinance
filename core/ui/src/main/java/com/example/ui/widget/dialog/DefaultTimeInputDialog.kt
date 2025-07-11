package com.example.ui.widget.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.shmrfinance.ui.R
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTimeInputDialog(
    onConfirmClick: (String) -> Unit,
    onDismissClick: () -> Unit
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    AlertDialog(
        text = {
            TimeInput(
                state = timePickerState
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmClick("${timePickerState.hour}:${timePickerState.minute}")
                    onDismissClick()
                }
            ) {
                Text(
                    text = stringResource(R.string.select),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissClick) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        onDismissRequest = onDismissClick
    )
}