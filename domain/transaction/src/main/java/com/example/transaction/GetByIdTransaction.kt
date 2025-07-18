package com.example.transaction

import com.example.data.external.remote.TransactionRepository
import com.example.model.Transaction
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetByIdTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) : UseCase<Int, Result<Transaction>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<Transaction> {
        return transactionRepository.getById(params)
    }
}