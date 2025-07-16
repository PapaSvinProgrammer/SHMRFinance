package com.example.room.internal.mapper

import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.model.TransactionResponse
import com.example.room.internal.component.transaction.TransactionEntity
import com.example.room.internal.component.transaction.TransactionResult
import com.example.utils.format.FormatDate
import java.math.BigDecimal

internal fun Transaction.toEntity(): TransactionEntity {
    return TransactionEntity(
        transactionId = id,
        accountId = account.id,
        categoryId = category.id,
        amount = amount.toString(),
        transactionDate = FormatDate.isoDateToMillis(transactionDate),
        comment = comment,
        isCreate = false,
        isUpdate = false,
        isDelete = false
    )
}

internal fun TransactionResult.toDomain(): Transaction {
    return Transaction(
        id = transaction.transactionId,
        account = bankAccount.toDomain(),
        category = category.toDomain(),
        amount = BigDecimal(transaction.amount),
        transactionDate = FormatDate.millisToIsoDate(transaction.transactionDate),
        comment = transaction.comment
    )
}

internal fun TransactionRequest.toEntityIsCreate(): TransactionEntity {
    return TransactionEntity(
       // id = 0,
        transactionId = 0,
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = FormatDate.isoDateToMillis(transactionDate),
        comment = comment,
        isCreate = true,
        isUpdate = false,
        isDelete = false
    )
}

internal fun TransactionRequest.toEntityIsUpdate(id: Int): TransactionEntity {
    return TransactionEntity(
        //id = 0,
        transactionId = id,
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = FormatDate.isoDateToMillis(transactionDate),
        comment = comment,
        isCreate = false,
        isUpdate = true,
        isDelete = false
    )
}

internal fun Int.toEntityIsDelete(): TransactionEntity {
    return TransactionEntity(
        transactionId = this,
        accountId = 0,
        categoryId = 0,
        amount = "",
        transactionDate = 0L,
        comment = null,
        isCreate = false,
        isUpdate = false,
        isDelete = true
    )
}

internal fun TransactionRequest.toResponse(): TransactionResponse {
    return TransactionResponse(
        id = 0,
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = transactionDate,
        comment = comment
    )
}