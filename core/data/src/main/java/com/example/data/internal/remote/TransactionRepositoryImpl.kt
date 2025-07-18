package com.example.data.internal.remote

import com.example.data.external.remote.TransactionRepository
import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.model.TransactionResponse
import com.example.network.connectivityState.NetworkConnection
import com.example.network.external.TransactionService
import com.example.room.external.TransactionServiceRoom
import javax.inject.Inject

internal class TransactionRepositoryImpl @Inject constructor(
    private val serviceRemote: TransactionService,
    private val serviceLocal: TransactionServiceRoom,
    private val networkConnection: NetworkConnection
) : TransactionRepository {
    override suspend fun create(request: TransactionRequest): Result<TransactionResponse> {
        if (networkConnection.isOnline()) {
            return serviceRemote.create(request)
        }

        return serviceLocal.create(request)
    }

    override suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Result<Transaction> {
        if (networkConnection.isOnline()) {
            return serviceRemote.update(
                id = id,
                request = request
            )
        }

        return serviceLocal.update(
            id = id,
            request = request
        )
    }

    override suspend fun delete(id: Int): Result<Unit> {
        if (networkConnection.isOnline()) {
            return serviceRemote.delete(id)
        }

        return serviceLocal.delete(id)
    }

    override suspend fun getById(id: Int): Result<Transaction> {
        if (networkConnection.isOnline()) {
            return serviceRemote.getById(id)
        }

        return serviceLocal.getById(id)
    }

    override suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>> {
        return if (networkConnection.isOnline()) {
            serviceRemote.getByPeriod(
                accountId = accountId,
                startDate = startDate,
                endDate = endDate
            )
        }
        else {
            serviceLocal.getByPeriod(
                accountId = accountId,
                startDate = startDate,
                endDate = endDate
            )
        }
    }
}