package com.example.network.internal.model

import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponseDto(
    val id: Int,
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String? = null,
    val createdAt: String,
    val updatedAt: String
)