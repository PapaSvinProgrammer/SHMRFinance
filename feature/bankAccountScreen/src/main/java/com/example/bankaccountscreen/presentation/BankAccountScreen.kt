package com.example.bankaccountscreen.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bankaccountscreen.presentation.widget.component.ChartContent
import com.example.bankaccountscreen.presentation.widget.model.ChartListItem
import com.example.model.BankAccount
import com.example.shmrfinance.ui.R
import com.example.ui.navigation.BankAccountListRoute
import com.example.ui.navigation.CreateBankAccountRoute
import com.example.ui.navigation.UpdateBankAccountRoute
import com.example.ui.uiState.BankAccountUIState
import com.example.ui.widget.components.BasicLoadingScreen
import com.example.ui.widget.components.CustomFloatingActionButton
import com.example.ui.widget.components.DefaultErrorContent
import com.example.ui.widget.components.EmojiCard
import com.example.ui.widget.listItems.TotalListItem
import com.example.utils.NetworkThrowable
import com.example.utils.format.ConvertData
import com.example.utils.toSlug

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BankAccountScreen(
    navController: NavController,
    viewModel: BankAccountViewModel
) {
    val currentBankAccount by viewModel.currentBankAccount.collectAsStateWithLifecycle()
    val currentChart by viewModel.currentChart.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getBankAccount()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.my_bank_account))
                },
                actions = {
                    IconButton(
                        onClick = {
                            navigateToUpdate(navController, currentBankAccount)
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = {
                            navController.navigate(BankAccountListRoute) {
                                launchSingleTop = true
                            }
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_notes),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when (val state = currentBankAccount) {
            is BankAccountUIState.Error -> {
                val error = (state.error as? NetworkThrowable)?.toSlug()
                DefaultErrorContent(error ?: "")
            }
            BankAccountUIState.Loading -> BasicLoadingScreen(Modifier.fillMaxSize())
            is BankAccountUIState.Success -> {
                MainContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding()),
                    account = state.data.first(),
                    currentChart = currentChart,
                    onClickCrateAccount = {
                        navController.navigate(CreateBankAccountRoute) {
                            launchSingleTop = true
                        }
                    },
                    onChangeChart = {
                        viewModel.updateCurrentChart(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    account: BankAccount,
    currentChart: ChartListItem,
    onClickCrateAccount: () -> Unit,
    onChangeChart: (ChartListItem) -> Unit
) {
    Box(modifier = modifier) {
        Column {
            TotalListItem(
                colors = ListItemDefaults.colors(MaterialTheme.colorScheme.primaryContainer),
                title = account.name,
                value = ConvertData.createPrettyAmount(
                    amount = account.balance,
                    currency = account.currency
                ),
                leadingContent = {
                    EmojiCard(
                        emoji = "\uD83D\uDCB0",
                        backgroundColor = MaterialTheme.colorScheme.background
                    )
                }
            )

            HorizontalDivider()

            TotalListItem(
                colors = ListItemDefaults.colors(MaterialTheme.colorScheme.primaryContainer),
                title = stringResource(R.string.currency),
                value = ConvertData.getCurrencySymbol(account.currency)
            )

            ChartContent(
                bankAccount = account,
                currentChart = currentChart,
                onChangeChart = onChangeChart
            )
        }

        CustomFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp),
            onClick = onClickCrateAccount
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.Add,
                contentDescription = null
            )
        }
    }
}

private fun navigateToUpdate(
    navController: NavController,
    state: BankAccountUIState
) {
    (state as? BankAccountUIState.Success)?.data?.firstOrNull()?.let {
        navController.navigate(UpdateBankAccountRoute(it.id)) {
            launchSingleTop = true
        }
    }
}
