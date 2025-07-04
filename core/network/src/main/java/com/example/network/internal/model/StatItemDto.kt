package com.example.network.internal.model

import kotlinx.serialization.Serializable

@Serializable
internal data class StatItemDto(
    val categoryId: Int,
    val categoryName: String,
    val emoji: String,
    val amount: Float
)