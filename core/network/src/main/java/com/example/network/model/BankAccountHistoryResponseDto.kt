package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BankAccountHistoryResponseDto(
    val accountId: Int,
    val accountName: String,
    val currency: String,
    val currentBalance: Float,
    val history: List<BankAccountHistoryDto>
)