package com.example.domain.useCase

import com.example.common.NetworkError
import com.example.common.Result
import com.example.common.request
import com.example.model.Transaction
import com.example.model.TransactionRequest
import jakarta.inject.Inject

class ChangeTransaction @Inject constructor(
    private val transactionRepository: com.example.data.repository.TransactionRepository
) {
    suspend fun create(request: TransactionRequest): Result<Transaction, NetworkError> {
        return request { transactionRepository.create(request) }
    }

    suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Result<Transaction, NetworkError> {
        return request {
            transactionRepository.update(
                id = id,
                request = request
            )
        }
    }

    suspend fun delete(id: Int): Result<Unit, NetworkError> {
        return request { transactionRepository.delete(id) }
    }
}