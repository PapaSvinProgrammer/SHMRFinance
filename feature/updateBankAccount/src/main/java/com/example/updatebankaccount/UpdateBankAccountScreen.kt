package com.example.updatebankaccount

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
import com.example.utils.ConvertData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateBankAccountScreen(
    navController: NavController,
    viewModel: UpdateBankAccountViewModel,
    bankAccountId: Int
) {
    val bankAccount by viewModel.bankAccountState.collectAsStateWithLifecycle()
    val visibleCurrencySheet by viewModel.visibleCurrencySheet.collectAsStateWithLifecycle()
    val visibleResultDialog by viewModel.visibleResultDialog.collectAsStateWithLifecycle()
    val resultState by viewModel.resultState.collectAsStateWithLifecycle()

    val name by viewModel.name.collectAsStateWithLifecycle()
    val balance by viewModel.balance.collectAsStateWithLifecycle()
    val currency by viewModel.currency.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getBankAccount(bankAccountId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Мой счет")
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
                            if (name.isNotEmpty()) {
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
                        name = name,
                        balance = balance,
                        currencyType = currency,
                        onValueNameChange = { viewModel.updateName(it) },
                        onValueBalanceChange = {},
                        balanceIsEnable = false,
                        isErrorName = name.isEmpty()
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

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
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

        if (visibleCurrencySheet) {
            CurrencyBottomSheet(
                onDismissRequest = {
                    viewModel.updateVisibleCurrencySheet(false)
                },
                onResult = {
                    viewModel.updateCurrency(it)
                    viewModel.updateVisibleCurrencySheet(false)
                }
            )
        }

        if (visibleResultDialog) {
            ResultDialog(
                type = resultState.toResultType(),
                error = (resultState as? BankAccountUIState.Error)?.error,
                onDismissRequest = { navController.popBackStack() }
            )
        }
    }
}