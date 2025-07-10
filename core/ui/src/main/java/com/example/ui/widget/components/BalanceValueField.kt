package com.example.ui.widget.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.utils.ConvertData
import java.math.BigDecimal

@Composable
fun BalanceValueField(
    value: BigDecimal,
    currency: String,
    onValueChange: (BigDecimal) -> Unit,
    enable: Boolean = true
) {
    val focusManager = LocalFocusManager.current
    val formatText = remember(value) { ConvertData.getPrettyBigDecimal(value) }
    val formatCurrency = remember(currency) { ConvertData.getCurrencySymbol(currency) }

    BasicTextField(
        modifier = Modifier.width(120.dp),
        value = "$formatText $formatCurrency",
        singleLine = true,
        enabled = enable,
        onValueChange = { onValueChange(it.toNormalBigDecimal()) },
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

private fun String.toNormalBigDecimal(): BigDecimal {
    var res = ""

    forEach {
        if (it.isDigit()) res += it
    }

    if (res.isEmpty()) return BigDecimal(0)

    return res.toBigDecimal()
}