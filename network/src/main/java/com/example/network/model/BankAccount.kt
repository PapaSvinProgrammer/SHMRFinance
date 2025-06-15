package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BankAccount(
    val id: String,
    val userId: Int? = null,
    val name: String,
    val balance: Float,
    val currency: String,
    val incomeStats: List<StatItem> = listOf(),
    val expensesStats: List<StatItem> = listOf(),
    val createdAt: String,
    val updatedAt: String
)