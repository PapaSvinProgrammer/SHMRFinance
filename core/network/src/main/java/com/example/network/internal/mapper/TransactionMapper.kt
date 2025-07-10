package com.example.network.internal.mapper

import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.model.TransactionResponse
import com.example.network.internal.model.TransactionDto
import com.example.network.internal.model.TransactionRequestDto
import com.example.network.internal.model.TransactionResponseDto

internal fun TransactionDto.toDomain(): Transaction {
    return Transaction(
        id = this.id,
        account = this.account.toDomain(),
        category = this.category.toDomain(),
        amount = this.amount.toBigDecimal(),
        transactionDate = this.transactionDate,
        comment = this.comment
    )
}

internal fun TransactionRequest.toDto(): TransactionRequestDto {
    return TransactionRequestDto(
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = transactionDate,
        comment = comment
    )
}

internal fun TransactionResponseDto.toDomain(): TransactionResponse {
    return TransactionResponse(
        id = id,
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = transactionDate,
        comment = comment
    )
}