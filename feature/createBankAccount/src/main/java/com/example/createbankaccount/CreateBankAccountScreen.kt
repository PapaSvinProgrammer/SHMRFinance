package com.example.createbankaccount

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
import com.example.shmrfinance.createBankAccount.R
import com.example.ui.dialog.ResultDialog
import com.example.ui.dialog.toResultType
import com.example.ui.uiState.BankAccountUIState
import com.example.ui.widget.bottomSheet.CurrencyBottomSheet
import com.example.ui.widget.components.DefaultBankAccountCard
import com.example.ui.widget.listItems.TransactionListItem
import com.example.utils.ConvertData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBankAccountScreen(
    navController: NavController,
    viewModel: CreateBankAccountViewModel
) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val balance by viewModel.balance.collectAsStateWithLifecycle()
    val currency by viewModel.currency.collectAsStateWithLifecycle()
    val errorName by viewModel.errorName.collectAsStateWithLifecycle()
    val isStartCreate by viewModel.isStartCreate.collectAsStateWithLifecycle()
    val accountState by viewModel.accountState.collectAsStateWithLifecycle()
    val visibleCurrencySheet by viewModel.visibleCurrencySheet.collectAsStateWithLifecycle()

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
                            if (name.isEmpty()) {
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
                name = name,
                balance = balance,
                currencyType = currency,
                onValueNameChange = { viewModel.updateName(it) },
                onValueBalanceChange = { viewModel.updateBalance(it) },
                isErrorName = errorName
            )

            HorizontalDivider()

            TransactionListItem(
                title = stringResource(R.string.currency),
                amount = ConvertData.getCurrencySymbol(currency.toSlug()),
                modifier = Modifier.clickable {
                    viewModel.updateVisibleCurrencySheet(true)
                }
            )

            HorizontalDivider()

            if (errorName) {
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    text = stringResource(R.string.name_account_error),
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        if (visibleCurrencySheet) {
            CurrencyBottomSheet(
                onDismissRequest = { viewModel.updateVisibleCurrencySheet(false) },
                onResult = {
                    viewModel.updateCurrency(it)
                    viewModel.updateVisibleCurrencySheet(false)
                }
            )
        }

        if (isStartCreate) {
            ResultDialog(
                type = accountState.toResultType(),
                error = (accountState as? BankAccountUIState.Error)?.error,
                onDismissRequest = {
                    viewModel.updateStartCreate(false)
                    navController.popBackStack()
                }
            )
        }
    }
}