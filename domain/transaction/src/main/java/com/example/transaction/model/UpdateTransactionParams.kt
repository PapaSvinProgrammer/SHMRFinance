package com.example.transaction.model

import com.example.model.TransactionRequest

data class UpdateTransactionParams(
    val id: Int,
    val request: TransactionRequest
)