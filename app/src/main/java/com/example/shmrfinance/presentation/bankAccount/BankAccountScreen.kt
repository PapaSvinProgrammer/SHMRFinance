package com.example.shmrfinance.presentation.bankAccount

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shmrfinance.R
import com.example.shmrfinance.app.utils.ConvertData
import com.example.shmrfinance.ui.widget.components.CustomFloatingActionButton
import com.example.shmrfinance.ui.widget.components.EmojiCard
import com.example.shmrfinance.ui.widget.listItems.TransactionListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankAccountScreen(
    navController: NavController,
    viewModel: BankAccountViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.my_bank_account))
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            Column {
                TransactionListItem(
                    colors = ListItemDefaults.colors(MaterialTheme.colorScheme.primaryContainer),
                    title = stringResource(R.string.balance),
                    amount = ConvertData.createPrettyAmount(
                        amount = 670000f,
                        currency = "RUB"
                    ),
                    leadingContent = {
                        EmojiCard(
                            emoji = "\uD83D\uDCB0",
                            backgroundColor = MaterialTheme.colorScheme.background
                        )
                    }
                )

                HorizontalDivider()

                TransactionListItem(
                    colors = ListItemDefaults.colors(MaterialTheme.colorScheme.primaryContainer),
                    title = stringResource(R.string.currency),
                    amount = ConvertData.getCurrencySymbol("RUB")
                )
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
}