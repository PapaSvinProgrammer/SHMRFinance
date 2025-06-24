package com.example.ui.widget.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CurrencyType
import com.example.model.toSlug
import com.example.shmrfinance.ui.R
import com.example.utils.ConvertData

@Composable
fun CreateBankAccountCard(
    name: String,
    balance: Float,
    currencyType: CurrencyType,
    onValueNameChange: (String) -> Unit,
    onValueBalanceChange: (Float) -> Unit,
    isErrorName: Boolean
) {
    ListItem(
        colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background),
        leadingContent = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null
            )
        },
        headlineContent = {
            AccountNameField(
                name = name,
                isError = isErrorName,
                onValueChange = onValueNameChange
            )
        },
        trailingContent = {
            BalanceValueField(
                value = balance,
                currency = currencyType.toSlug(),
                onValueChange = onValueBalanceChange
            )
        }
    )
}

@Composable
private fun AccountNameField(
    name: String,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val color = if (isError)
        MaterialTheme.colorScheme.error
    else
        MaterialTheme.colorScheme.onSurfaceVariant

    BasicTextField(
        modifier = Modifier,
        value = name,
        singleLine = true,
        onValueChange = onValueChange,
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
        decorationBox = { innerTextField ->
            if (name.isEmpty()) {
                Text(
                    text = stringResource(R.string.name_account),
                    color = color
                )
            }

            innerTextField()
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}

@Composable
private fun BalanceValueField(
    value: Float,
    currency: String,
    onValueChange: (Float) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val formatText = remember(value) { ConvertData.getPrettyInt(value) }
    val formatCurrency = remember(currency) { ConvertData.getCurrencySymbol(currency) }

    BasicTextField(
        modifier = Modifier.width(120.dp),
        value = "$formatText $formatCurrency",
        singleLine = true,
        onValueChange = { onValueChange(it.toNormalFloat()) },
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.End
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.NumberPassword
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}

private fun String.toNormalFloat(): Float {
    var res = ""

    forEach {
        if (it.isDigit()) res += it
    }

    if (res.isEmpty()) return 0f

    return res.toFloat()
}