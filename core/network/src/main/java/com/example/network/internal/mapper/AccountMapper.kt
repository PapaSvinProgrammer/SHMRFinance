package com.example.network.internal.mapper

import com.example.model.AccountRequest
import com.example.model.AccountState
import com.example.model.BankAccount
import com.example.model.BankAccountHistory
import com.example.model.BankAccountHistoryResponse
import com.example.network.internal.model.AccountRequestDto
import com.example.network.internal.model.AccountStateDto
import com.example.network.internal.model.BankAccountDto
import com.example.network.internal.model.BankAccountHistoryDto
import com.example.network.internal.model.BankAccountHistoryResponseDto

internal fun BankAccountDto.toDomain(): BankAccount {
    return BankAccount(
        id = this.id,
        userId = this.userId,
        name = this.name,
        balance = this.balance.toBigDecimal(),
        currency = this.currency,
        incomeStats = this.incomeStats.toDomain(),
        expensesStats = this.expensesStats.toDomain()
    )
}

internal fun List<BankAccountDto>.toDomain(): List<BankAccount> {
    return map { it.toDomain() }
}

internal fun AccountStateDto?.toDomain(): AccountState? {
    if (this == null) return null

    return AccountState(
        id = this.id,
        name = this.name,
        balance = this.balance.toBigDecimal(),
        currency = this.currency
    )
}

internal fun BankAccountHistoryDto.toDomain(): BankAccountHistory {
    return BankAccountHistory(
        id = this.id,
        accountId = this.accountId,
        changeType = this.changeType,
        previousState = this.previousState.toDomain(),
        newState = this.newState.toDomain(),
        changeTimestamp = this.changeTimestamp
    )
}

internal fun BankAccountHistoryResponseDto.toDomain(): BankAccountHistoryResponse {
    return BankAccountHistoryResponse(
        accountId = this.accountId,
        accountName = this.accountName,
        currency = this.currency,
        currentBalance = this.currentBalance.toBigDecimal(),
        history = this.history.map { it.toDomain() }
    )
}

internal fun AccountRequest.toDto(): AccountRequestDto {
    return AccountRequestDto(
        name = this.name,
        balance = this.balance,
        currency = this.currency
    )
}