package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    val id: Int,
    val account: BankAccountDto,
    val category: CategoryDto,
    val amount: Float,
    val transactionDate: String,
    val comment: String? = null,
    val createdAt: String,
    val updatedAt: String
)