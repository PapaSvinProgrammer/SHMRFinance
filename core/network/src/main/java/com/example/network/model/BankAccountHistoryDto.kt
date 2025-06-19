package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BankAccountHistoryDto(
    val id: Int,
    val accountId: Int,
    val changeType: String,
    val previousState: AccountStateDto? = null,
    val newState: AccountStateDto? = null,
    val changeTimestamp: String,
    val createdAt: String
)