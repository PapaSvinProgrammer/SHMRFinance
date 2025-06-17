package com.example.shmrfinance.ui.widget.bottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shmrfinance.R
import com.example.shmrfinance.domain.model.CurrencyType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyBottomSheet(
    onDismissRequest: () -> Unit,
    onResult: (CurrencyType) -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismissRequest) {
        Column {
            currencyList.forEach {
                ListItem(
                    modifier = Modifier
                        .height(70.dp)
                        .clickable { onResult(it.type) },
                    leadingContent = {
                        Text(
                            text = it.symbol,
                            fontSize = 20.sp
                        )
                    },
                    headlineContent = {
                        Text(text = stringResource(it.title))
                    },
                    colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceContainerLow)
                )

                HorizontalDivider()
            }

            ListItem(
                modifier = Modifier
                    .height(70.dp)
                    .clickable(onClick = onDismissRequest),
                leadingContent = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_cancel),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onError
                    )
                },
                headlineContent = {
                    Text(
                        text = stringResource(R.string.cancel),
                        color = MaterialTheme.colorScheme.onError
                    )
                },
                colors = ListItemDefaults.colors(MaterialTheme.colorScheme.errorContainer)
            )
        }
    }
}

private data class CurrencyItem(
    val title: Int,
    val symbol: String,
    val type: CurrencyType
)

private val currencyList = listOf(
    CurrencyItem(
        title = R.string.russian_currency,
        symbol = "₽",
        type = CurrencyType.RUB
    ),
    CurrencyItem(
        title = R.string.american_currency,
        symbol = "$",
        type = CurrencyType.USD
    ),
    CurrencyItem(
        title = R.string.euro_currency,
        symbol = "€",
        type = CurrencyType.EUR
    )
)