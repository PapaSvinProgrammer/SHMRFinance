package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountRequest(
    val name: String,
    val balance: String,
    val currency: String
)