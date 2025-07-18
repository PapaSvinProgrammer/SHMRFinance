package com.example.transactionhistory.presentation.widget

import java.math.BigDecimal

internal data class UiState(
    val totalAmount: BigDecimal = BigDecimal.ZERO,
    val currency: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val visibleStartDatePicker: Boolean = false,
    val visibleEndDatePicker: Boolean = false,
)

internal data class VisibleState(
    val startDatePicker: Boolean = false,
    val endDatePicker: Boolean = false
)