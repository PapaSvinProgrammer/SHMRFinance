package com.example.network.internal.model

import kotlinx.serialization.Serializable

@Serializable
internal data class AccountRequestDto(
    val name: String,
    val balance: String,
    val currency: String
)