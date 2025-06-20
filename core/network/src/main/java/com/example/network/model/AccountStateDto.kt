package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountStateDto(
    val id: Int,
    val name: String,
    val balance: Float,
    val currency: String
)