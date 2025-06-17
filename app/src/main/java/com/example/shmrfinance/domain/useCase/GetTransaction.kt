package com.example.shmrfinance.domain.useCase

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.request
import com.example.network.model.Transaction
import com.example.shmrfinance.domain.repository.TransactionRepository
import jakarta.inject.Inject

class GetTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend fun getById(id: Int): Operation<Transaction, NetworkError> {
        return request { transactionRepository.getById(id) }
    }

    suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Operation<List<Transaction>, NetworkError> {
        return request {
            transactionRepository.getByPeriod(
                accountId = accountId,
                startDate = startDate,
                endDate = endDate
            )
        }
    }
}