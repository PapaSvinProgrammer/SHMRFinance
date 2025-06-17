package com.example.shmrfinance.domain.useCase

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.request
import com.example.network.model.BankAccount
import com.example.network.model.BankAccountHistoryResponse
import com.example.shmrfinance.domain.repository.BankAccountRepository
import jakarta.inject.Inject

class GetBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun getById(id: Int): Operation<BankAccount, NetworkError> {
        return request { bankAccountRepository.getById(id) }
    }

    suspend fun getAll(): Operation<List<BankAccount>, NetworkError> {
        return request { bankAccountRepository.getAll() }
    }

    suspend fun getUpdateHistory(id: Int): Operation<BankAccountHistoryResponse, NetworkError> {
        return request { bankAccountRepository.getUpdateHistory(id) }
    }
}