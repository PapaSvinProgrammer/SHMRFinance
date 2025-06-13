package com.example.shmrfinance.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shmrfinance.R
import com.example.shmrfinance.app.utils.ConvertData
import com.example.shmrfinance.transactionList
import com.example.shmrfinance.ui.widget.components.CustomFloatingActionButton
import com.example.shmrfinance.ui.widget.components.EmojiCard
import com.example.shmrfinance.ui.widget.listItems.TotalListItem
import com.example.shmrfinance.ui.widget.listItems.TransactionListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.expenses_today))
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_history),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize()
        ) {
            Column {
                TotalListItem(
                    title = stringResource(R.string.total),
                    value = ConvertData.createPrettyAmount(
                        amount = 436558f,
                        currency = "RUB"
                    )
                )

                HorizontalDivider()

                LazyColumn {
                    items(transactionList) {
                        TransactionListItem(
                            modifier = Modifier
                                .height(70.dp)
                                .clickable { },
                            title = it.category.name,
                            subtitle = it.comment,
                            leadingContent = { EmojiCard(emoji = it.category.emoji) },
                            amount = ConvertData.createPrettyAmount(
                                amount = it.amount,
                                currency = it.account.currency
                            )
                        )

                        HorizontalDivider()
                    }
                }
            }

            CustomFloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(15.dp),
                onClick = {  }
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null
                )
            }
        }
    }
}