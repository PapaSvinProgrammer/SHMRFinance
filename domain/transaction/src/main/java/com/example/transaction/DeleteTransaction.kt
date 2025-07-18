package com.example.transaction

import com.example.data.external.remote.TransactionRepository
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DeleteTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) : UseCase<Int, Result<Unit>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<Unit> {
        return transactionRepository.delete(params)
    }
}