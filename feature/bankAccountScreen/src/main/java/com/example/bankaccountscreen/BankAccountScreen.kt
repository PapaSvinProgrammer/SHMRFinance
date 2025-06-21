package com.example.bankaccountscreen

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.model.BankAccount
import com.example.navigationroute.BankAccountListScreenRoute
import com.example.navigationroute.CreateBankAccountRoute
import com.example.ui.uiState.BankAccountUIState
import com.example.ui.widget.components.BasicLoadingScreen
import com.example.ui.widget.components.CustomFloatingActionButton
import com.example.ui.widget.components.EmojiCard
import com.example.ui.widget.listItems.TotalListItem
import com.example.utils.ConvertData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankAccountScreen(
    navController: NavController,
    viewModel: BankAccountViewModel = hiltViewModel()
) {
    val currentBankAccount by viewModel.currentBankAccount.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.my_bank_account))
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = {
                            navController.navigate(BankAccountListScreenRoute) {
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
            is BankAccountUIState.Error -> {}
            BankAccountUIState.Loading -> BasicLoadingScreen(Modifier.fillMaxSize())
            is BankAccountUIState.Success -> {
                MainContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding()),
                    account = state.data.first(),
                    onClickCrateAccount = {
                        navController.navigate(CreateBankAccountRoute) {
                            launchSingleTop = true
                        }
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
    onClickCrateAccount: () -> Unit
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