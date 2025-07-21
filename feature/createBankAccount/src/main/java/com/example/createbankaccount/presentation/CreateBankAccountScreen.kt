package com.example.createbankaccount.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.model.toSlug
import com.example.shmrfinance.ui.R
import com.example.ui.dialog.ResultDialog
import com.example.ui.dialog.toResultType
import com.example.ui.uiState.BankAccountUIState
import com.example.ui.widget.bottomSheet.CurrencyBottomSheet
import com.example.ui.widget.components.DefaultBankAccountCard
import com.example.ui.widget.listItems.TransactionListItem
import com.example.utils.format.ConvertData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateBankAccountScreen(
    navController: NavController,
    viewModel: CreateBankAccountViewModel
) {
    val accountState by viewModel.accountState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val visibleState by viewModel.visibleState.collectAsStateWithLifecycle()

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
                            if (uiState.name.isEmpty()) {
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
            DefaultBankAccountCard(
                name = uiState.name,
                balance = uiState.balance,
                currencyType = uiState.currency,
                onValueNameChange = { viewModel.updateName(it) },
                onValueBalanceChange = { viewModel.updateBalance(it) },
                isErrorName = uiState.errorName
            )

            HorizontalDivider()

            TransactionListItem(
                title = stringResource(R.string.currency),
                amount = ConvertData.getCurrencySymbol(uiState.currency.toSlug()),
                modifier = Modifier.clickable {
                    viewModel.updateVisibleCurrencySheet(true)
                }
            )

            HorizontalDivider()

            if (uiState.errorName) {
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    text = stringResource(R.string.name_account_error),
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        if (visibleState.currencySheet) {
            CurrencyBottomSheet(
                onDismissRequest = { viewModel.updateVisibleCurrencySheet(false) },
                onResult = { viewModel.updateCurrency(it) }
            )
        }

        if (visibleState.resultDialog) {
            ResultDialog(
                type = accountState.toResultType(),
                error = (accountState as? BankAccountUIState.Error)?.error,
                onDismissRequest = {
                    viewModel.updateVisibleResultDialog(false)
                    navController.popBackStack()
                }
            )
        }
    }
}