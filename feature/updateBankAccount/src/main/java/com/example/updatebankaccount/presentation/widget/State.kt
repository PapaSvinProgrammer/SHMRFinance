package com.example.updatebankaccount.presentation.widget

import com.example.model.CurrencyType
import java.math.BigDecimal

data class UiState(
    val name: String = "",
    val balance: BigDecimal = BigDecimal.ZERO,
    val currency: CurrencyType = CurrencyType.RUB,
    val errorName: Boolean = false
)

data class VisibleState(
    val resultDialog: Boolean = false,
    val currencySheet: Boolean = false
)
