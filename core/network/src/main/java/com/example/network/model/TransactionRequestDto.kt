package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class TransactionRequestDto(
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String? = null
)