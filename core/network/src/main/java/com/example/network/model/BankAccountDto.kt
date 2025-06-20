package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BankAccountDto(
    val id: Int,
    val userId: Int? = null,
    val name: String,
    val balance: Float,
    val currency: String,
    val incomeStats: List<StatItemDto> = listOf(),
    val expensesStats: List<StatItemDto> = listOf(),
    val createdAt: String = "",
    val updatedAt: String = ""
)