package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountRequestDto(
    val name: String,
    val balance: String,
    val currency: String
)