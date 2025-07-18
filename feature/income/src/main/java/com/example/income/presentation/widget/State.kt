package com.example.income.presentation.widget

import java.math.BigDecimal

internal data class UiState(
    val totalAmount: BigDecimal = BigDecimal.ZERO,
    val currency: String? = null
)