package com.example.transactionhistory.presentation

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
import androidx.navigation.NavController
import com.example.model.Transaction
import com.example.shmrfinance.ui.R
import com.example.ui.navigation.TransactionAnalysisRoute
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
internal fun TransactionHistoryScreen(
    navController: NavController,
    isIncome: Boolean,
    viewModel: TransactionHistoryViewModel
) {
    val transactionState by viewModel.transactionState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val visibleState by viewModel.visibleState.collectAsStateWithLifecycle()

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
                    IconButton(
                        onClick = {
                            navController.navigate(TransactionAnalysisRoute(isIncome)) {
                                launchSingleTop = true
                            }
                        }
                    ) {
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
                    currency = uiState.currency,
                    startDate = FormatDate.getPrettyDate(uiState.startDate),
                    endDate = FormatDate.getPrettyDate(uiState.endDate),
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

    if (visibleState.startDatePicker) {
        DefaultDatePickerDialog(
            selectedDate = FormatDate.convertStringToMillis(uiState.startDate),
            onDateSelected = {
                viewModel.updateStartDate(it ?: 0)
                viewModel.getTransaction(isIncome)
            },
            onDismiss = {
                viewModel.updateVisibleStartDatePicker(false)
            }
        )
    }

    if (visibleState.endDatePicker) {
        DefaultDatePickerDialog(
            selectedDate = FormatDate.convertStringToMillis(uiState.endDate),
            onDateSelected = {
                viewModel.updateEndDate(it ?: 0)
                viewModel.getTransaction(isIncome)
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
    totalAmount: BigDecimal,
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

        TransactionContent(list) {
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
}
