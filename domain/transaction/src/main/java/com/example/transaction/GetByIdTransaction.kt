package com.example.transaction

import com.example.common.request
import com.example.data.external.TransactionRepository
import com.example.model.Transaction
import javax.inject.Inject

class GetByIdTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend fun getById(id: Int): Result<Transaction> {
        return request { transactionRepository.getById(id) }
    }
}