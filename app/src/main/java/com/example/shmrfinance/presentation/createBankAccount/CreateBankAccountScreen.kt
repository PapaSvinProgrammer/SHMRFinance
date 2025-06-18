package com.example.shmrfinance.presentation.createBankAccount

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shmrfinance.R
import com.example.shmrfinance.app.utils.ConvertData
import com.example.shmrfinance.domain.model.toSlug
import com.example.shmrfinance.ui.dialog.ResultDialog
import com.example.shmrfinance.ui.dialog.ResultDialogType
import com.example.shmrfinance.ui.uiState.BankAccountUIState
import com.example.shmrfinance.ui.widget.bottomSheet.CurrencyBottomSheet
import com.example.shmrfinance.ui.widget.components.CreateBankAccountCard
import com.example.shmrfinance.ui.widget.listItems.TransactionListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBankAccountScreen(
    navController: NavController,
    viewModel: CreateBankAccountViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.my_bank_account))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (viewModel.name.isEmpty()) {
                                viewModel.updateErrorName(true)
                            }
                            else {
                                viewModel.createBankAccount()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            CreateBankAccountCard(
                name = viewModel.name,
                balance = viewModel.balance,
                currencyType = viewModel.currency,
                onValueNameChange = { viewModel.updateName(it) },
                onValueBalanceChange = { viewModel.updateBalance(it) },
                isErrorName = viewModel.errorName
            )

            HorizontalDivider()

            TransactionListItem(
                title = stringResource(R.string.currency),
                amount = ConvertData.getCurrencySymbol(viewModel.currency.toSlug()),
                modifier = Modifier.clickable {
                    viewModel.updateVisibleCurrencySheet(true)
                }
            )

            HorizontalDivider()

            if (viewModel.errorName) {
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    text = stringResource(R.string.name_account_error),
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        if (viewModel.visibleCurrencySheet) {
            CurrencyBottomSheet(
                onDismissRequest = { viewModel.updateVisibleCurrencySheet(false) },
                onResult = {
                    viewModel.updateCurrency(it)
                    viewModel.updateVisibleCurrencySheet(false)
                }
            )
        }

        if (viewModel.isStartCreate) {
            val type = when (viewModel.accountState) {
                is BankAccountUIState.Error -> ResultDialogType.ERROR
                BankAccountUIState.Loading -> ResultDialogType.LOADING
                is BankAccountUIState.Success -> ResultDialogType.SUCCESS
            }

            ResultDialog(
                type = type,
                error = (viewModel.accountState as? BankAccountUIState.Error)?.error,
                onDismissRequest = {
                    viewModel.updateStartCreate(false)
                    navController.popBackStack()
                }
            )
        }
    }
}