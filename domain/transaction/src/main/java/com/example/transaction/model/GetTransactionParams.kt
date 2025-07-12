package com.example.transaction.model

data class GetTransactionParams(
    val isIncome: Boolean = false,
    val accountId: Int,
    val startDate: String,
    val endDate: String
)
