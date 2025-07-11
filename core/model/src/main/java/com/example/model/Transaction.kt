package com.example.model

import java.math.BigDecimal

data class Transaction(
    val id: Int,
    val account: BankAccount,
    val category: Category,
    val amount: BigDecimal,
    val transactionDate: String,
    val comment: String? = null
)