package com.example.room.internal.service

import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.model.TransactionResponse
import com.example.room.external.TransactionServiceRoom
import com.example.room.internal.component.transaction.TransactionDao
import com.example.room.internal.mapper.toDomain
import com.example.room.internal.mapper.toEntity
import com.example.room.internal.mapper.toEntityIsCreate
import com.example.room.internal.mapper.toEntityIsDelete
import com.example.room.internal.mapper.toEntityIsUpdate
import com.example.room.internal.mapper.toResponse
import com.example.room.internal.safeCall
import javax.inject.Inject

internal class TransactionServiceRoomImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionServiceRoom {
    override suspend fun create(transactionRequest: TransactionRequest): Result<TransactionResponse> {
        return safeCall {
            dao.update(transactionRequest.toEntityIsCreate())
        }.map { transactionRequest.toResponse() }
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return safeCall {
            dao.update(id.toEntityIsDelete())
        }
    }

    override suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Result<Transaction> {
        return safeCall {
            dao.update(request.toEntityIsUpdate(id))
            dao.getById(id)!!
        }.map { it.toDomain() }
    }

    override suspend fun insertAll(list: List<Transaction>): Result<Unit> {
        val list = list.map { it.toEntity() }.toTypedArray()

        return safeCall {
            dao.insertAll(*list)
        }
    }

    override suspend fun deleteAll(): Result<Unit> {
        return safeCall {
            dao.deleteAll()
        }
    }

    override suspend fun getById(id: Int): Result<Transaction> {
        return safeCall {
            dao.getById(id)!!
        }.map { it.toDomain() }
    }

    override suspend fun getAll(): Result<List<Transaction>> {
        return safeCall {
            dao.getAll()
        }.map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>> {
        TODO("Not yet implemented")
    }
}
