package com.example.shmrfinance.domain.useCase

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.request
import com.example.network.model.AccountRequest
import com.example.network.model.BankAccount
import com.example.shmrfinance.domain.repository.BankAccountRepository
import jakarta.inject.Inject

class ChangeBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun create(accountRequest: AccountRequest): Operation<BankAccount, NetworkError> {
        return request { bankAccountRepository.create(accountRequest) }
    }

    suspend fun update(
        id: Int,
        accountRequest: AccountRequest
    ): Operation<BankAccount, NetworkError> {
        return request {
            bankAccountRepository.update(
                id = id,
                request = accountRequest
            )
        }
    }

    suspend fun delete(id: Int): Operation<Unit, NetworkError> {
        return request { bankAccountRepository.delete(id) }
    }
}