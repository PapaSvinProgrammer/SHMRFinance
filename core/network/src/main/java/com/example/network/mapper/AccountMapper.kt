package com.example.network.mapper

import com.example.model.AccountRequest
import com.example.model.AccountState
import com.example.model.BankAccount
import com.example.model.BankAccountHistory
import com.example.model.BankAccountHistoryResponse
import com.example.network.model.AccountRequestDto
import com.example.network.model.AccountStateDto
import com.example.network.model.BankAccountDto
import com.example.network.model.BankAccountHistoryDto
import com.example.network.model.BankAccountHistoryResponseDto

fun BankAccountDto.toDomain(): BankAccount {
    return BankAccount(
        id = this.id,
        userId = this.userId,
        name = this.name,
        balance = this.balance,
        currency = this.currency,
        incomeStats = this.incomeStats.toDomain(),
        expensesStats = this.expensesStats.toDomain()
    )
}

fun List<BankAccountDto>.toDomain(): List<BankAccount> {
    return map { it.toDomain() }
}

fun AccountStateDto?.toDomain(): AccountState? {
    if (this == null) return null

    return AccountState(
        id = this.id,
        name = this.name,
        balance = this.balance,
        currency = this.currency
    )
}

fun BankAccountHistoryDto.toDomain(): BankAccountHistory {
    return BankAccountHistory(
        id = this.id,
        accountId = this.accountId,
        changeType = this.changeType,
        previousState = this.previousState.toDomain(),
        newState = this.newState.toDomain(),
        changeTimestamp = this.changeTimestamp
    )
}

fun BankAccountHistoryResponseDto.toDomain(): BankAccountHistoryResponse {
    return BankAccountHistoryResponse(
        accountId = this.accountId,
        accountName = this.accountName,
        currency = this.currency,
        currentBalance = this.currentBalance,
        history = this.history.map { it.toDomain() }
    )
}

fun AccountRequest.toDto(): AccountRequestDto {
    return AccountRequestDto(
        name = this.name,
        balance = this.balance,
        currency = this.name
    )
}