package com.example.network.internal.model

import kotlinx.serialization.Serializable

@Serializable
internal data class AccountStateDto(
    val id: Int,
    val name: String,
    val balance: Float,
    val currency: String
)