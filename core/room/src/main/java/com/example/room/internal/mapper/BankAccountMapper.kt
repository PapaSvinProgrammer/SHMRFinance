package com.example.room.internal.mapper

import com.example.model.BankAccount
import com.example.room.internal.component.bankAccount.BankAccountEntity
import java.math.BigDecimal

internal fun BankAccountEntity.toDomain(): BankAccount {
    return BankAccount(
        id = accountId,
        userId = userId,
        name = name,
        balance = BigDecimal(balance),
        currency = currency,
    )
}

internal fun BankAccount.toEntity(): BankAccountEntity {
    return BankAccountEntity(
        id = 0,
        accountId = id,
        userId = userId,
        name = name,
        balance = balance.toString(),
        currency = currency
    )
}

internal fun List<BankAccountEntity>.toDomain(): List<BankAccount> {
    return map { it.toDomain() }
}

internal fun List<BankAccount>.toEntity(): List<BankAccountEntity> {
    return map { it.toEntity() }
}