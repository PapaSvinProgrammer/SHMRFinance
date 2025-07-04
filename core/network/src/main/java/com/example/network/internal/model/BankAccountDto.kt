package com.example.network.internal.model

import kotlinx.serialization.Serializable

@Serializable
internal data class BankAccountDto(
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