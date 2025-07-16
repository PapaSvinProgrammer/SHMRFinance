package com.example.data.internal.local

import com.example.data.external.local.TransactionRepositoryRoom
import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.model.TransactionResponse
import com.example.room.external.TransactionServiceRoom
import javax.inject.Inject

class TransactionRepositoryRoomImpl @Inject constructor(
    private val dao: TransactionServiceRoom
) : TransactionRepositoryRoom {
    override suspend fun create(transaction: TransactionRequest): Result<TransactionResponse> {
        return dao.create(transaction)
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return dao.delete(id)
    }

    override suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Result<Transaction> {
        return dao.update(id, request)
    }

    override suspend fun insertAll(list: List<Transaction>): Result<Unit> {
        return dao.insertAll(list)
    }

    override suspend fun deleteAll(): Result<Unit> {
        return dao.deleteAll()
    }

    override suspend fun getById(id: Int): Result<Transaction> {
        return dao.getById(id)
    }

    override suspend fun getAll(): Result<List<Transaction>> {
        return dao.getAll()
    }

    override suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>> {
        return dao.getByPeriod(accountId, startDate, endDate)
    }

    override suspend fun getCreated(): Result<List<TransactionRequest>> {
        return dao.getCreated()
    }

    override suspend fun getUpdated(): Result<List<Transaction>> {
        return dao.getUpdated()
    }

    override suspend fun getDeleted(): Result<List<Int>> {
        return dao.getDeleted()
    }
}