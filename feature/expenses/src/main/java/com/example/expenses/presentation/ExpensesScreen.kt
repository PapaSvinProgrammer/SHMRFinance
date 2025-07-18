package com.example.expenses.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.model.Transaction
import com.example.ui.navigation.CreateTransactionRoute
import com.example.ui.navigation.TransactionHistoryRoute
import com.example.ui.navigation.UpdateTransactionRoute
import com.example.shmrfinance.expenses.R
import com.example.ui.uiState.TransactionUIState
import com.example.ui.widget.components.BasicLoadingScreen
import com.example.ui.widget.components.CustomFloatingActionButton
import com.example.ui.widget.components.DefaultErrorContent
import com.example.ui.widget.components.EmojiCard
import com.example.ui.widget.components.TransactionContent
import com.example.ui.widget.listItems.TotalListItem
import com.example.ui.widget.listItems.TransactionListItem
import com.example.utils.NetworkThrowable
import com.example.utils.format.ConvertData
import com.example.utils.toSlug
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExpensesScreen(
    navController: NavController,
    viewModel: ExpensesViewModel
) {
    val transactionState by viewModel.transactionState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getTransactions()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.expenses_today))
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(
                                TransactionHistoryRoute(isIncome = false)
                            ) { launchSingleTop = true }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_history),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when (val state = transactionState) {
            is TransactionUIState.Error -> {
                val error = (state.error as? NetworkThrowable)?.toSlug()
                DefaultErrorContent(error ?: "")
            }
            TransactionUIState.Loading -> BasicLoadingScreen(Modifier.fillMaxSize())
            is TransactionUIState.Success -> {
                MainContent(
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                    list = state.data,
                    totalAmount = uiState.totalAmount,
                    currency = uiState.currency ?: "",
                    onFabClick = {
                        navController.navigate(CreateTransactionRoute(false)) {
                            launchSingleTop = true
                        }
                    },
                    onTransactionClick = {
                        navController.navigate(
                            UpdateTransactionRoute(
                                id = it.id
                            )
                        ) { launchSingleTop = true }
                    }
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    list: List<Transaction>,
    totalAmount: BigDecimal,
    currency: String,
    onFabClick: () -> Unit,
    onTransactionClick: (Transaction) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            TotalListItem(
                title = stringResource(R.string.total),
                value = ConvertData.createPrettyAmount(
                    amount = totalAmount,
                    currency = currency
                )
            )

            HorizontalDivider()

            TransactionContent(list) {
                LazyColumn {
                    items(
                        items = list,
                        key = { it.id }
                    ) {
                        TransactionListItem(
                            modifier = Modifier
                                .height(70.dp)
                                .clickable { onTransactionClick(it) },
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
        }

        CustomFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp),
            onClick = onFabClick
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.Add,
                contentDescription = null
            )
        }
    }
}