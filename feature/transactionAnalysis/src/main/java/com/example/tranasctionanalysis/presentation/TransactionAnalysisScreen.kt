package com.example.tranasctionanalysis.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.charts.DonutChart
import com.example.model.Transaction
import com.example.shmrfinance.transactionAnalysis.R
import com.example.tranasctionanalysis.presentation.widget.DateListItem
import com.example.ui.uiState.TransactionUIState
import com.example.ui.widget.components.BasicLoadingScreen
import com.example.ui.widget.components.DefaultErrorContent
import com.example.ui.widget.components.EmojiCard
import com.example.ui.widget.components.TransactionContent
import com.example.ui.widget.dialog.DefaultDatePickerDialog
import com.example.ui.widget.listItems.TotalListItem
import com.example.ui.widget.listItems.TransactionListItem
import com.example.utils.NetworkThrowable
import com.example.utils.format.ConvertData
import com.example.utils.format.FormatDate
import com.example.utils.toSlug
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TransactionAnalysisScreen(
    navController: NavController,
    viewModel: TransactionAnalysisViewModel,
    isIncome: Boolean
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val visibleState by viewModel.visibleState.collectAsStateWithLifecycle()
    val transactionState by viewModel.transactionState.collectAsStateWithLifecycle()
    val chartData by viewModel.chartData.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getTransactions(isIncome)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.analysis)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        when (val state = transactionState) {
            is TransactionUIState.Error -> {
                val error = (state.error as? NetworkThrowable)?.toSlug()
                DefaultErrorContent(error ?: "")
            }

            TransactionUIState.Loading -> {
                BasicLoadingScreen(Modifier.fillMaxSize())
            }

            is TransactionUIState.Success -> {
                MainContent(
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                    chartData = chartData,
                    list = state.data,
                    totalAmount = uiState.total,
                    currency = uiState.currency,
                    startDate = FormatDate.getPrettyDate(uiState.startDate),
                    endDate = FormatDate.getPrettyDate(uiState.endDate),
                    onStartDateChange = {
                        viewModel.updateStartDatePickerVisible(true)
                    },
                    onEndDateChange = {
                        viewModel.updateEndDatePickerVisible(true)
                    }
                )
            }
        }
    }

    if (visibleState.startDatePicker) {
        DefaultDatePickerDialog(
            selectedDate = FormatDate.convertStringToMillis(uiState.startDate),
            onDateSelected = {
                viewModel.updateStartDate(it ?: 0L)
                viewModel.getTransactions(isIncome)
            },
            onDismiss = {
                viewModel.updateStartDatePickerVisible(false)
            }
        )
    }

    if (visibleState.endDatePicker) {
        DefaultDatePickerDialog(
            selectedDate = FormatDate.convertStringToMillis(uiState.endDate),
            onDateSelected = {
                viewModel.updateEndDate(it ?: 0L)
                viewModel.getTransactions(isIncome)
            },
            onDismiss = {
                viewModel.updateEndDatePickerVisible(false)
            }
        )
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    chartData: Map<Float, String>,
    list: List<Transaction>,
    totalAmount: BigDecimal,
    currency: String,
    startDate: String,
    endDate: String,
    onStartDateChange: () -> Unit,
    onEndDateChange: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        DateListItem(
            text = stringResource(R.string.period_start),
            date = startDate,
            onDateClick = onStartDateChange
        )

        HorizontalDivider()

        DateListItem(
            text = stringResource(R.string.period_end),
            date = endDate,
            onDateClick = onEndDateChange
        )

        HorizontalDivider()

        TotalListItem(
            title = stringResource(R.string.sum),
            value = ConvertData.createPrettyAmount(
                amount = totalAmount,
                currency = currency
            ),
            colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background)
        )

        HorizontalDivider()

        TransactionContent(list) {
            LazyColumn {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        DonutChart(data = chartData)
                    }
                }

                items(
                    items = list,
                    key = { it.id }
                ) {
                    TransactionListItem(
                        modifier = Modifier.height(70.dp),
                        title = it.category.name,
                        leadingContent = { EmojiCard(emoji = it.category.emoji) },
                        subtitle = it.comment,
                        time = ConvertData.createPrettyAmount(
                            amount = it.amount,
                            currency = it.account.currency
                        ),
                        amount = ConvertData.createPrettyPercent(
                            amount = it.amount,
                            total = totalAmount
                        )
                    )

                    HorizontalDivider()
                }
            }
        }
    }
}