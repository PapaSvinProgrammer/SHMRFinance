package com.example.domain.useCase

import com.example.common.NetworkError
import com.example.common.Result
import com.example.common.request
import com.example.data.repository.BankAccountRepository
import com.example.model.AccountRequest
import com.example.model.BankAccount
import jakarta.inject.Inject

class ChangeBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun create(accountRequest: AccountRequest): Result<BankAccount, NetworkError> {
        return request { bankAccountRepository.create(accountRequest) }
    }

    suspend fun update(
        id: Int,
        accountRequest: AccountRequest
    ): Result<BankAccount, NetworkError> {
        return request {
            bankAccountRepository.update(
                id = id,
                request = accountRequest
            )
        }
    }

    suspend fun delete(id: Int): Result<Unit, NetworkError> {
        return request { bankAccountRepository.delete(id) }
    }
}