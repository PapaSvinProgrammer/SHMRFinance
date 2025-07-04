package com.example.data.external

import com.example.model.Transaction
import com.example.model.TransactionRequest

interface TransactionRepository {
    suspend fun create(request: TransactionRequest): Result<Transaction>
    suspend fun update(id: Int, request: TransactionRequest): Result<Transaction>
    suspend fun delete(id: Int): Result<Unit>
    suspend fun getById(id: Int): Result<Transaction>
    suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>>
}