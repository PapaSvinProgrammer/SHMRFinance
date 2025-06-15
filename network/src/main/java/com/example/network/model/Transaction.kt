package com.example.core

import com.example.network.model.Category

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