package com.example.model

data class BankAccount(
    val id: Int,
    val userId: Int? = null,
    val name: String,
    val balance: Float,
    val currency: String,
    val incomeStats: List<StatItem> = listOf(),
    val expensesStats: List<StatItem> = listOf()
)