package com.example.model

data class BankAccountHistory(
    val id: Int,
    val accountId: Int,
    val changeType: String,
    val previousState: AccountState? = null,
    val newState: AccountState? = null,
    val changeTimestamp: String
)