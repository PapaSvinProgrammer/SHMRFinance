package com.example.shmrfinance.presentation.income

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.model.Transaction
import com.example.shmrfinance.R
import com.example.shmrfinance.navigation.TransactionHistoryRoute
import com.example.shmrfinance.utils.ConvertData
import com.example.shmrfinance.ui.widget.components.BasicLoadingScreen
import com.example.shmrfinance.ui.widget.components.CustomFloatingActionButton
import com.example.shmrfinance.ui.widget.listItems.TotalListItem
import com.example.shmrfinance.ui.widget.listItems.TransactionListItem
import com.example.shmrfinance.ui.uiState.TransactionUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(
    navController: NavController,
    viewModel: IncomeViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.incomes_today))
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(
                                TransactionHistoryRoute(isIncome = true)
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
        when (val state = viewModel.transactionState) {
            is TransactionUIState.Error -> {}
            TransactionUIState.Loading -> BasicLoadingScreen(Modifier.fillMaxSize())
            is TransactionUIState.Success -> MainContent(
                modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                list = state.data,
                totalAmount = viewModel.totalAmount.toFloat(),
                currency = viewModel.currency ?: ""
            )
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    list: List<Transaction>,
    totalAmount: Float,
    currency: String
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

            LazyColumn {
                items(
                    items = list,
                    key = { it.id }
                ) {
                    TransactionListItem(
                        modifier = Modifier
                            .height(72.dp)
                            .clickable { },
                        title = it.category.name,
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