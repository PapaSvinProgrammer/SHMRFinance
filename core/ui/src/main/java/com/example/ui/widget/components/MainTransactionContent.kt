package com.example.ui.widget.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.model.BankAccount
import com.example.model.Category
import com.example.shmrfinance.ui.R
import com.example.ui.widget.listItems.TotalListItem
import com.example.ui.widget.listItems.TransactionListItem
import java.math.BigDecimal

@Composable
fun MainTransactionContent(
    modifier: Modifier,
    bankAccount: BankAccount,
    balance: BigDecimal,
    category: Category?,
    date: String,
    time: String,
    comment: String,
    onBankAccountClick: () -> Unit,
    onCategoryClick: () -> Unit,
    onDateClick: () -> Unit,
    onTimeClick: () -> Unit,
    onChangeBalance: (BigDecimal) -> Unit,
    onCommentChange: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TransactionListItem(
            modifier = Modifier
                .height(70.dp)
                .clickable(onClick = onBankAccountClick),
            title = stringResource(R.string.bank_account),
            amount = bankAccount.name
        )

        HorizontalDivider()

        TransactionListItem(
            modifier = Modifier
                .height(70.dp)
                .clickable(onClick = onCategoryClick),
            title = stringResource(R.string.article),
            amount = category?.name ?: ""
        )

        HorizontalDivider()

        ListItem(
            modifier = Modifier.height(70.dp),
            colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background),
            headlineContent = {
                Text(
                    text = stringResource(R.string.sum),
                )
            },
            trailingContent = {
                BalanceValueField(
                    value = balance,
                    currency = bankAccount.currency,
                    onValueChange = onChangeBalance
                )
            }
        )

        HorizontalDivider()

        TotalListItem(
            modifier = Modifier
                .height(70.dp)
                .clickable(onClick = onDateClick),
            colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background),
            title = stringResource(R.string.date),
            value = date
        )

        HorizontalDivider()

        TotalListItem(
            modifier = Modifier
                .height(70.dp)
                .clickable(onClick = onTimeClick),
            colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background),
            title = stringResource(R.string.time),
            value = time
        )

        HorizontalDivider()

        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            text = comment,
            hint = stringResource(R.string.comment_hint),
            onValueChange = onCommentChange
        )
    }
}
