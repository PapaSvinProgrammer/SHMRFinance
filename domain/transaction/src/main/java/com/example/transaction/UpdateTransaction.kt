package com.example.transaction

import com.example.data.repository.TransactionRepository
import com.example.model.Transaction
import com.example.model.TransactionRequest
import javax.inject.Inject

class UpdateTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend fun execute(
        id: Int,
        request: TransactionRequest
    ): Result<Transaction> {
        return com.example.common.request {
            transactionRepository.update(
                id = id,
                request = request
            )
        }
    }
}