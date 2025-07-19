package com.example.tranasctionanalysis.presentation.widget.state

import java.math.BigDecimal

internal data class UIState(
    val startDate: String = "",
    val endDate: String = "",
    val total: BigDecimal = BigDecimal.ZERO,
    val currency: String = ""
)