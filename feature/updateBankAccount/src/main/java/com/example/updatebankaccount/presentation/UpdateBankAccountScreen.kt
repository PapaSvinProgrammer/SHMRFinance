package com.example.updatebankaccount.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.model.toSlug
import com.example.shmrfinance.updateBankAccount.R
import com.example.ui.dialog.ResultDialog
import com.example.ui.dialog.toResultType
import com.example.ui.uiState.BankAccountUIState
import com.example.ui.widget.bottomSheet.CurrencyBottomSheet
import com.example.ui.widget.components.BasicLoadingScreen
import com.example.ui.widget.components.DefaultBankAccountCard
import com.example.ui.widget.listItems.TransactionListItem
import com.example.utils.format.ConvertData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UpdateBankAccountScreen(
    navController: NavController,
    viewModel: UpdateBankAccountViewModel,
    bankAccountId: Int
) {
    val resultState by viewModel.resultState.collectAsStateWithLifecycle()
    val bankAccount by viewModel.accountState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val visible by viewModel.visibleState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getBankAccount(bankAccountId)
    }

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
                            if (uiState.name.isNotEmpty()) {
                                viewModel.updateBankAccount(bankAccountId)
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
        when (bankAccount) {
            is BankAccountUIState.Error -> {}
            BankAccountUIState.Loading -> BasicLoadingScreen(Modifier.fillMaxSize())
            is BankAccountUIState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    DefaultBankAccountCard(
                        name = uiState.name,
                        balance = uiState.balance,
                        currencyType = uiState.currency,
                        onValueNameChange = { viewModel.updateName(it) },
                        onValueBalanceChange = {},
                        balanceIsEnable = false,
                        isErrorName = uiState.name.isEmpty()
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

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        ),
                        onClick = {
                            viewModel.deleteBankAccount(bankAccountId)
                        }
                    ) {
                        Text(text = stringResource(R.string.delete_bank_account))
                    }
                }
            }
        }

        if (visible.currencySheet) {
            CurrencyBottomSheet(
                onDismissRequest = {
                    viewModel.updateVisibleCurrencySheet(false)
                },
                onResult = { viewModel.updateCurrency(it) }
            )
        }

        if (visible.resultDialog) {
            ResultDialog(
                type = resultState.toResultType(),
                error = (resultState as? BankAccountUIState.Error)?.error,
                onDismissRequest = {
                    viewModel.updateVisibleResultDialog(false)
                    navController.popBackStack()
                }
            )
        }
    }
}