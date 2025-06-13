package com.example.core

data class BankAccount(
    val id: String,
    val userId: Int? = null,
    val name: String,
    val balance: Float,
    val currency: String,
    val incomeStats: List<StatItem> = listOf(),
    val expensesStats: List<StatItem> = listOf(),
    val createdAt: String,
    val updatedAr: String
)