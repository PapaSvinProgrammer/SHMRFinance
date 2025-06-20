package com.example.network.mapper

import com.example.model.Transaction
import com.example.network.model.TransactionDto

fun TransactionDto.toDomain(): Transaction {
    return Transaction(
        id = this.id,
        account = this.account.toDomain(),
        category = this.category.toDomain(),
        amount = this.amount,
        transactionDate = this.transactionDate,
        comment = this.comment
    )
}

