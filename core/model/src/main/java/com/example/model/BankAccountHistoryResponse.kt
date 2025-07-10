package com.example.model

import java.math.BigDecimal

data class BankAccountHistoryResponse(
    val accountId: Int,
    val accountName: String,
    val currency: String,
    val currentBalance: BigDecimal,
    val history: List<BankAccountHistory>
)