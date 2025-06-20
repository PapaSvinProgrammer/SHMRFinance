package com.example.model

data class BankAccountHistoryResponse(
    val accountId: Int,
    val accountName: String,
    val currency: String,
    val currentBalance: Float,
    val history: List<BankAccountHistory>
)