package com.example.model

data class Transaction(
    val id: Int,
    val account: BankAccount,
    val category: Category,
    val amount: Float,
    val transactionDate: String,
    val comment: String? = null
)