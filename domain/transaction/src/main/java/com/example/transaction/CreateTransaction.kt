package com.example.transaction

import com.example.common.request
import com.example.data.external.TransactionRepository
import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.model.TransactionResponse
import javax.inject.Inject

class CreateTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend fun execute(request: TransactionRequest): Result<TransactionResponse> {
        return request { transactionRepository.create(request) }
    }
}