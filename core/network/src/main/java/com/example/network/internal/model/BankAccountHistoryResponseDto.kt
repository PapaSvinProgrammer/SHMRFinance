package com.example.network.internal.model

import kotlinx.serialization.Serializable

@Serializable
internal data class BankAccountHistoryResponseDto(
    val accountId: Int,
    val accountName: String,
    val currency: String,
    val currentBalance: Float,
    val history: List<BankAccountHistoryDto>
)