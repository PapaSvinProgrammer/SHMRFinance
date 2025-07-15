package com.example.transaction

import com.example.data.external.local.TransactionRepositoryRoom
import com.example.data.external.remote.TransactionRepository
import com.example.model.Transaction
import com.example.network.connectivityState.NetworkConnection
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetByIdTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val transactionRepositoryRoom: TransactionRepositoryRoom,
    private val networkConnection: NetworkConnection
) : UseCase<Int, Result<Transaction>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<Transaction> {
        if (networkConnection.isOnline()) {
            return transactionRepository.getById(params)
        }

        return transactionRepositoryRoom.getById(params)
    }
}