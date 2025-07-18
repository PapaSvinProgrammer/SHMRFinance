package com.example.transaction

import com.example.data.external.local.TransactionRepositoryRoom
import com.example.model.Transaction
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SaveTransaction @Inject constructor(
    private val transactionRepositoryRoom: TransactionRepositoryRoom
) : UseCase<List<Transaction>, Result<Unit>>(Dispatchers.IO) {
    override suspend fun run(params: List<Transaction>): Result<Unit> {
        return transactionRepositoryRoom.insertAll(params)
    }
}