package com.example.transaction

import com.example.common.request
import com.example.data.repository.TransactionRepository
import jakarta.inject.Inject

class DeleteTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend fun execute(id: Int): Result<Unit> {
        return request { transactionRepository.delete(id) }
    }
}