package com.example.domain.useCase

import com.example.common.NetworkError
import com.example.common.Result
import com.example.common.request
import com.example.data.repository.BankAccountRepository
import com.example.model.BankAccount
import com.example.model.BankAccountHistoryResponse
import jakarta.inject.Inject

class GetBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun getById(id: Int): Result<BankAccount, NetworkError> {
        return request { bankAccountRepository.getById(id) }
    }

    suspend fun getAll(): Result<List<BankAccount>, NetworkError> {
        return request { bankAccountRepository.getAll() }
    }

    suspend fun getUpdateHistory(id: Int): Result<BankAccountHistoryResponse, NetworkError> {
        return request { bankAccountRepository.getUpdateHistory(id) }
    }
}