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
import com.example.room.internal.mapper.toInt
import com.example.room.internal.mapper.toRequest
import com.example.room.internal.mapper.toResponse
import com.example.room.internal.safeCall
import com.example.utils.RoomThrowable
import com.example.utils.format.FormatDate
import javax.inject.Inject

internal class TransactionServiceRoomImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionServiceRoom {
    override suspend fun create(transactionRequest: TransactionRequest): Result<TransactionResponse> {
        return safeCall {
            dao.insertAll(transactionRequest.toEntityIsCreate())
        }.map { transactionRequest.toResponse() }
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return safeCall {
            val res = dao.update(id.toEntityIsDelete())

            if (res == 0) throw RoomThrowable(null)
        }
    }

    override suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Result<Transaction> {
        return safeCall {
            val res = dao.update(request.toEntityIsUpdate(id))

            if (res == 0) throw RoomThrowable(null)

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
            val transaction = dao.getById(id)
            if (transaction == null) throw RoomThrowable("Not found")
            transaction
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
        return safeCall {
            dao.getByPeriod(
                id = accountId,
                start = FormatDate.dateToMillis(startDate),
                end = FormatDate.dateToMillis(endDate) + MILLIS_IN_DAY
            )
        }.map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getCreated(): Result<List<TransactionRequest>> {
        return safeCall {
            dao.getOnlyCreated()
        }.map { list ->
            list.map { it.toRequest() }
        }
    }

    override suspend fun getUpdated(): Result<List<Transaction>> {
        return safeCall {
            dao.getOnlyUpdated()
        }.map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getDeleted(): Result<List<Int>> {
        return safeCall {
            dao.getOnlyDeleted()
        }.map { list ->
            list.map { it.toInt() }
        }
    }

    private companion object {
        const val MILLIS_IN_DAY = 86_400_000L
    }
}
