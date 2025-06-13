package com.example.core

data class Transaction(
    val id: Int,
    val account: BankAccount,
    val category: Category,
    val amount: Float,
    val transactionDate: String,
    val comment: String? = null,
    val createdAt: String,
    val updatedAt: String
)