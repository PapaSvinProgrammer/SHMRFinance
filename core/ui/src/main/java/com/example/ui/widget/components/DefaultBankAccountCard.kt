package com.example.ui.widget.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.model.CurrencyType
import com.example.model.toSlug
import com.example.shmrfinance.ui.R
import java.math.BigDecimal

@Composable
fun DefaultBankAccountCard(
    name: String,
    balance: BigDecimal,
    currencyType: CurrencyType,
    onValueNameChange: (String) -> Unit,
    onValueBalanceChange: (BigDecimal) -> Unit,
    balanceIsEnable: Boolean = true,
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
            CustomTextField(
                text = name,
                hint = stringResource(R.string.name_account),
                isError = isErrorName,
                singleLine = true,
                onValueChange = onValueNameChange
            )
        },
        trailingContent = {
            BalanceValueField(
                value = balance,
                currency = currencyType.toSlug(),
                onValueChange = onValueBalanceChange,
                enable = balanceIsEnable
            )
        }
    )
}
