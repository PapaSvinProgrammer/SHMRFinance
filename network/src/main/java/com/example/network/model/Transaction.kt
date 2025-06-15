package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
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