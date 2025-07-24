package com.example.bankaccountscreen.presentation.widget.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bankaccountscreen.presentation.widget.model.ChartListItem
import com.example.bankaccountscreen.presentation.widget.model.chartsList
import com.example.charts.ColumnChart
import com.example.charts.LineChart
import com.example.model.BankAccount

@Composable
internal fun ChartContent(
    bankAccount: BankAccount,
    currentChart: ChartListItem,
    onChangeChart: (ChartListItem) -> Unit
) {
    Box {
        when (currentChart) {
            ChartListItem.ColumnChart -> ColumnPrepareChart(bankAccount)
            ChartListItem.LineChart -> LinePrepareChart(bankAccount)
        }

        SelectableDropDownList(
            current = currentChart,
            list = chartsList,
            onClick = onChangeChart
        )
    }
}

@Composable
private fun ColumnPrepareChart(bankAccount: BankAccount) {
    val incomeList = bankAccount.incomeStats.map { it.amount }
    val expenseList = bankAccount.expensesStats.map { -it.amount }
    val dataList = (incomeList + expenseList).map { it.toFloat() }

    if (dataList.isEmpty()) return

    ColumnChart(
        y = dataList,
        modifier = Modifier.padding(top = 55.dp)
    )
}

@Composable
private fun LinePrepareChart(bankAccount: BankAccount) {
    val dataList = bankAccount.incomeStats.map { it.amount.toFloat() }

    if (dataList.isEmpty()) return

    LineChart(
        data = dataList,
        modifier = Modifier.padding(top = 55.dp)
    )
}