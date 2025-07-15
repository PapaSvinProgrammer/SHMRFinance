package com.example.transaction

import com.example.data.external.local.TransactionRepositoryRoom
import com.example.data.external.remote.TransactionRepository
import com.example.model.Transaction
import com.example.network.connectivityState.NetworkConnection
import com.example.transaction.model.UpdateTransactionParams
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UpdateTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val transactionRepositoryRoom: TransactionRepositoryRoom,
    private val networkConnection: NetworkConnection
) : UseCase<UpdateTransactionParams, Result<Transaction>>(Dispatchers.IO) {
    override suspend fun run(params: UpdateTransactionParams): Result<Transaction> {
        if (networkConnection.isOnline()) {
            return transactionRepository.update(
                id = params.id,
                request = params.request
            )
        }

        return transactionRepositoryRoom.update(
            id = params.id,
            request = params.request
        )
    }
}
