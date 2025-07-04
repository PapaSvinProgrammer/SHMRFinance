package com.example.network.internal.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CategoryDto(
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)