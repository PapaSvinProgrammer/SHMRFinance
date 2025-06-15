package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BankAccountHistory(
    val id: Int,
    val accountId: Int,
    val changeType: String,
    val previousState: AccountState? = null,
    val newState: AccountState? = null,
    val changeTimestamp: String,
    val createdAt: String
)