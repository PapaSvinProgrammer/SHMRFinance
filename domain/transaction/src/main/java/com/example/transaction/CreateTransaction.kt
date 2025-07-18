package com.example.transaction

import com.example.data.external.remote.TransactionRepository
import com.example.model.TransactionRequest
import com.example.model.TransactionResponse
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CreateTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) : UseCase<TransactionRequest, Result<TransactionResponse>>(Dispatchers.IO) {
    override suspend fun run(params: TransactionRequest): Result<TransactionResponse> {
        return transactionRepository.create(params)
    }
}