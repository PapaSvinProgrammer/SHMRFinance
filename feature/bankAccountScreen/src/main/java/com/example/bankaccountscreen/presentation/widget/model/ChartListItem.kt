package com.example.bankaccountscreen.presentation.widget.model

import com.example.shmrfinance.ui.R

sealed class ChartListItem(
    val icon: Int,
    val text: Int
) {
    data object ColumnChart : ChartListItem(
        icon = R.drawable.ic_bar_chart,
        text = R.string.column
    )

    data object LineChart : ChartListItem(
        icon = R.drawable.ic_show_chart,
        text = R.string.line
    )
}

internal val chartsList = listOf(
    ChartListItem.ColumnChart,
    ChartListItem.LineChart
)
