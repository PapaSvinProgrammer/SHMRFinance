package com.example.network.internal.model

import kotlinx.serialization.Serializable

@Serializable
internal data class TransactionDto(
    val id: Int,
    val account: BankAccountDto,
    val category: CategoryDto,
    val amount: Float,
    val transactionDate: String,
    val comment: String? = null,
    val createdAt: String,
    val updatedAt: String
)