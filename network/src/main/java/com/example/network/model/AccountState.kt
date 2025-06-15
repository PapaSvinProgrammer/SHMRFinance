package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountState(
    val id: Int,
    val name: String,
    val balance: Float,
    val currency: String
)