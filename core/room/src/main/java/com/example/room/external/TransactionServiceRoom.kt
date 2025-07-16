package com.example.room.external

import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.model.TransactionResponse

interface TransactionServiceRoom {
    suspend fun create(transactionRequest: TransactionRequest): Result<TransactionResponse>
    suspend fun delete(id: Int): Result<Unit>
    suspend fun update(id: Int, request: TransactionRequest): Result<Transaction>

    suspend fun insertAll(list: List<Transaction>): Result<Unit>
    suspend fun deleteAll(): Result<Unit>
    suspend fun getById(id: Int): Result<Transaction>
    suspend fun getAll(): Result<List<Transaction>>
    suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>>

    suspend fun getCreated(): Result<List<TransactionRequest>>
    suspend fun getUpdated(): Result<List<Transaction>>
    suspend fun getDeleted(): Result<List<Int>>
}