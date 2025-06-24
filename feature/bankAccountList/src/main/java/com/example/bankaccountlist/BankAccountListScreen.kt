package com.example.bankaccountlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.model.BankAccount
import com.example.shmrfinance.bankAccountList.R
import com.example.ui.uiState.BankAccountUIState
import com.example.ui.widget.components.BasicLoadingScreen
import com.example.utils.ConvertData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankAccountListScreen(
    navController: NavController,
    viewModel: BankAccountListViewModel = hiltViewModel()
) {
    val accountState by viewModel.accountState.collectAsStateWithLifecycle()
    val accountId by viewModel.accountId.collectAsStateWithLifecycle(null)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.all_bank_accounts)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when (val state = accountState) {
            is BankAccountUIState.Error -> {}
            BankAccountUIState.Loading -> BasicLoadingScreen(Modifier.fillMaxSize())
            is BankAccountUIState.Success -> {
                MainContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding()),
                    list = state.data,
                    currentId = accountId,
                    onClick = { viewModel.updateAccountId(it) }
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    list: List<BankAccount>,
    currentId: Int?,
    onClick: (Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(
            items = list,
            key = { it.id }
        ) {
            ListItem(
                modifier = Modifier.clickable { onClick(it.id) },
                colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background),
                headlineContent = {
                    Column {
                        Text(
                            text = it.name,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        Text(
                            text = ConvertData.createPrettyAmount(
                                amount = it.balance,
                                currency = it.currency
                            ),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                trailingContent = {
                    if (currentId == it.id) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                }
            )

            HorizontalDivider()
        }
    }
}