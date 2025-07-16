package com.example.tranasctionanalysis.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shmrfinance.transactionAnalysis.R
import com.example.ui.widget.listItems.TotalListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionAnalysisScreen(
    navController: NavController,
    viewModel: TransactionAnalysisViewModel,
    isIncome: Boolean
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.analysis)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize()
        ) {
            DateListItem(
                text = "Период: начало",
                date = "123213",
                onDateClick = {

                }
            )

            HorizontalDivider()

            DateListItem(
                text = "Период: конец",
                date = "123213",
                onDateClick = {

                }
            )

            HorizontalDivider()

            TotalListItem(
                title = stringResource(R.string.sum),
                value = "123 Р",
                colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background)
            )

            HorizontalDivider()
        }
    }
}

@Composable
private fun DateListItem(
    text: String,
    date: String,
    onDateClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(text) },
        trailingContent = {
            SuggestionChip(
                onClick = onDateClick,
                label = {
                    Text(
                        text = date,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = SuggestionChipDefaults.suggestionChipColors(MaterialTheme.colorScheme.primary),
                border = null,
                shape = CircleShape
            )
        },
        colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background)
    )
}
