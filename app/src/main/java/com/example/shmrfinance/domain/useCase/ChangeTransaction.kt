package com.example.shmrfinance.domain.useCase

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.request
import com.example.network.model.Transaction
import com.example.network.model.TransactionRequest
import com.example.shmrfinance.domain.repository.TransactionRepository
import jakarta.inject.Inject

class ChangeTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend fun create(request: TransactionRequest): Operation<Transaction, NetworkError> {
        return request { transactionRepository.create(request) }
    }

    suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Operation<Transaction, NetworkError> {
        return request {
            transactionRepository.update(
                id = id,
                request = request
            )
        }
    }

    suspend fun delete(id: Int): Operation<Unit, NetworkError> {
        return request { transactionRepository.delete(id) }
    }
}