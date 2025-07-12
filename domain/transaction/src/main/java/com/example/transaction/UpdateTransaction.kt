package com.example.transaction

import com.example.data.external.TransactionRepository
import com.example.model.Transaction
import com.example.transaction.model.UpdateTransactionParams
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UpdateTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) : UseCase<UpdateTransactionParams, Result<Transaction>>(Dispatchers.IO) {
    override suspend fun run(params: UpdateTransactionParams): Result<Transaction> {
        return transactionRepository.update(
            id = params.id,
            request = params.request
        )
    }
}
