package com.example.bankaccountscreen.model

import com.example.model.AccountRequest

data class UpdateBankParams(
    val id: Int,
    val request: AccountRequest
)
