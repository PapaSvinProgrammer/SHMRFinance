package com.example.transaction

import com.example.data.external.local.TransactionRepositoryRoom
import com.example.data.external.remote.TransactionRepository
import com.example.network.connectivityState.NetworkConnection
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DeleteTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val transactionRepositoryRoom: TransactionRepositoryRoom,
    private val networkConnection: NetworkConnection
) : UseCase<Int, Result<Unit>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<Unit> {
        if (networkConnection.isOnline()) {
            return transactionRepository.delete(params)
        }

        return transactionRepositoryRoom.delete(params)
    }
}