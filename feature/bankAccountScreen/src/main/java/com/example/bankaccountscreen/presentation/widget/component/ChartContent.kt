package com.example.bankaccountscreen.presentation.widget.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bankaccountscreen.presentation.widget.model.ChartType
import com.example.bankaccountscreen.presentation.widget.model.DropDownItem
import com.example.charts.ColumnChart
import com.example.model.BankAccount

@Composable
internal fun ChartContent(
    bankAccount: BankAccount,
    chartType: ChartType,
    onChangeChartType: () -> Unit
) {
    val incomeList = bankAccount.incomeStats.map { it.amount }
    val expenseList = bankAccount.expensesStats.map { -it.amount }
    val dataList = (incomeList + expenseList).map { it.toFloat() }

    if (dataList.isEmpty()) return

    Box {
        when (chartType) {
            ChartType.COLUMN -> {
                ColumnChart(
                    y = dataList,
                    modifier = Modifier.padding(top = 55.dp)
                )
            }
            ChartType.LINE -> {

            }
        }

        SelectableDropDownList(
            current = DropDownItem(
                icon = Icons.Default.AccountBox,
                text = "COLUMN",
                chartType = ChartType.COLUMN
            ),
            list = listOf(
                DropDownItem(
                    icon = Icons.Default.AccountBox,
                    text = "COLUMN",
                    chartType = ChartType.COLUMN
                ),
                DropDownItem(
                    icon = Icons.Default.AccountBox,
                    text = "LINE",
                    chartType = ChartType.LINE
                )
            ),
            onClick = {}
        )
    }
}