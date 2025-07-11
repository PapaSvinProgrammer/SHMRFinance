package com.example.ui.widget.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    isError: Boolean = false,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val color = if (isError)
        MaterialTheme.colorScheme.error
    else
        MaterialTheme.colorScheme.onSurfaceVariant

    BasicTextField(
        modifier = modifier,
        value = text,
        singleLine = singleLine,
        onValueChange = onValueChange,
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .verticalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        color = color
                    )
                }

                innerTextField()
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}