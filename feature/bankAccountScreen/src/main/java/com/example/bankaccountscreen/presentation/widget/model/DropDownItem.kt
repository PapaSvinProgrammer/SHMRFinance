package com.example.bankaccountscreen.presentation.widget.model

import androidx.compose.ui.graphics.vector.ImageVector

internal data class DropDownItem(
    val icon: ImageVector,
    val text: String,
    val chartType: ChartType
)