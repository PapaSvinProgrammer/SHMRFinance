package com.example.data.external.local

import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.model.TransactionResponse

interface TransactionRepositoryRoom {
    suspend fun create(transaction: TransactionRequest): Result<TransactionResponse>
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
}