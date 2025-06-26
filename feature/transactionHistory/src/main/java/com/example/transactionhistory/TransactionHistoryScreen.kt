package com.example.transactionhistory

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.localviewmodelfactory.LocalViewModelFactory
import com.example.model.Transaction
import com.example.shmrfinance.transactionHistory.R
import com.example.ui.uiState.TransactionUIState
import com.example.ui.widget.components.BasicLoadingScreen
import com.example.ui.widget.components.DefaultDatePicker
import com.example.ui.widget.components.EmojiCard
import com.example.ui.widget.listItems.TotalListItem
import com.example.ui.widget.listItems.TransactionListItem
import com.example.utils.ConvertData
import com.example.utils.FormatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionHistoryScreen(
    navController: NavController,
    isIncome: Boolean
) {
    val viewModel: TransactionHistoryViewModel = viewModel(factory = LocalViewModelFactory.current)

    val transactionState by viewModel.transactionState.collectAsStateWithLifecycle()
    val totalAmount by viewModel.totalAmount.collectAsStateWithLifecycle()
    val currency by viewModel.currency.collectAsStateWithLifecycle()
    val startDate by viewModel.startDate.collectAsStateWithLifecycle()
    val endDate by viewModel.endDate.collectAsStateWithLifecycle()
    val visibleStartDatePicker by viewModel.visibleStartDatePicker.collectAsStateWithLifecycle()
    val visibleEndDatePicker by viewModel.visibleEndDatePicker.collectAsStateWithLifecycle()

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
        when (val state = transactionState) {
            is TransactionUIState.Error -> {}
            TransactionUIState.Loading -> BasicLoadingScreen(Modifier.fillMaxSize())
            is TransactionUIState.Success -> {
                MainContent(
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                    list = state.data,
                    totalAmount = totalAmount.toFloat(),
                    currency = currency,
                    startDate = FormatDate.getPrettyDate(startDate),
                    endDate = FormatDate.getPrettyDate(endDate),
                    onStartDateChange = {
                        viewModel.updateVisibleStartDatePicker(true)
                    },
                    onEndDateChange = {
                        viewModel.updateVisibleEndDatePicker(true)
                    }
                )
            }
        }
    }

    if (visibleStartDatePicker) {
        DefaultDatePicker(
            selectedDate = FormatDate.convertStringToMillis(startDate),
            onDateSelected = {
                viewModel.updateStartDate(it ?: 0)
            },
            onDismiss = {
                viewModel.updateVisibleStartDatePicker(false)
            }
        )
    }

    if (visibleEndDatePicker) {
        DefaultDatePicker(
            selectedDate = FormatDate.convertStringToMillis(endDate),
            onDateSelected = {
                viewModel.updateEndDate(it ?: 0)
            },
            onDismiss = {
                viewModel.updateVisibleEndDatePicker(false)
            }
        )
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    list: List<Transaction>,
    totalAmount: Float,
    currency: String,
    startDate: String,
    endDate: String,
    onStartDateChange: () -> Unit,
    onEndDateChange: () -> Unit
) {
    Column(modifier = modifier) {
        TotalListItem(
            modifier = Modifier.clickable(onClick = onStartDateChange),
            title = stringResource(R.string.start),
            value = startDate
        )
        HorizontalDivider()
        TotalListItem(
            modifier = Modifier.clickable(onClick = onEndDateChange),
            title = stringResource(R.string.end),
            value = endDate
        )
        HorizontalDivider()
        TotalListItem(
            title = stringResource(R.string.total),
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
                    time = FormatDate.getPrettyDayAndTime(it.transactionDate),
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