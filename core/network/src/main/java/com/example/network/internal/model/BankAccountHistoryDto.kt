package com.example.network.internal.model

import kotlinx.serialization.Serializable

@Serializable
internal data class BankAccountHistoryDto(
    val id: Int,
    val accountId: Int,
    val changeType: String,
    val previousState: AccountStateDto? = null,
    val newState: AccountStateDto? = null,
    val changeTimestamp: String,
    val createdAt: String
)