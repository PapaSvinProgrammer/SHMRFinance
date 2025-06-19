package com.example.shmrfinance.presentation.transactionHistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.model.Transaction
import com.example.shmrfinance.R
import com.example.shmrfinance.ui.uiState.TransactionUIState
import com.example.shmrfinance.ui.widget.components.BasicLoadingScreen
import com.example.shmrfinance.ui.widget.components.EmojiCard
import com.example.shmrfinance.ui.widget.listItems.TotalListItem
import com.example.shmrfinance.ui.widget.listItems.TransactionListItem
import com.example.shmrfinance.utils.ConvertData
import com.example.shmrfinance.utils.FormatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionHistoryScreen(
    navController: NavController,
    viewModel: TransactionHistoryViewModel = hiltViewModel(),
    isIncome: Boolean
) {
    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getTransaction(isIncome)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.my_history)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_manage),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when (val state = viewModel.transactionState) {
            is TransactionUIState.Error -> {}
            TransactionUIState.Loading -> BasicLoadingScreen(Modifier.fillMaxSize())
            is TransactionUIState.Success -> {
                MainContent(
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                    list = state.data,
                    totalAmount = viewModel.totalAmount.toFloat(),
                    currency = viewModel.currency,
                    startDate = FormatDate.getPrettyDate(viewModel.startDate),
                    endDate = FormatDate.getPrettyDate(viewModel.endDate)
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    list: List<Transaction>,
    totalAmount: Float,
    currency: String,
    startDate: String,
    endDate: String
) {
    Column(modifier = modifier) {
        TotalListItem(
            title = stringResource(R.string.start),
            value = startDate
        )
        HorizontalDivider()
        TotalListItem(
            title = stringResource(R.string.end),
            value = endDate
        )
        HorizontalDivider()
        TotalListItem(
            title = stringResource(R.string.sum),
            value = ConvertData.createPrettyAmount(
                amount = totalAmount,
                currency = currency
            )
        )

        LazyColumn {
            items(
                items = list,
                key = { it.id }
            ) {
                TransactionListItem(
                    modifier = Modifier.height(70.dp),
                    title = it.category.name,
                    leadingContent = { EmojiCard(emoji = it.category.emoji) },
                    subtitle = it.comment,
                    time = FormatDate.getPrettyTime(it.transactionDate),
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