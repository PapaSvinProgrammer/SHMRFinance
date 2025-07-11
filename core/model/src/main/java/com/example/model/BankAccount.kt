package com.example.model

import java.math.BigDecimal

data class BankAccount(
    val id: Int,
    val userId: Int? = null,
    val name: String,
    val balance: BigDecimal,
    val currency: String,
    val incomeStats: List<StatItem> = listOf(),
    val expensesStats: List<StatItem> = listOf()
)