package com.example.data.repository

import com.example.common.NetworkError
import com.example.common.Result
import com.example.model.Transaction
import com.example.model.TransactionRequest

interface TransactionRepository {
    suspend fun create(request: TransactionRequest): Result<Transaction, NetworkError>
    suspend fun update(id: Int, request: TransactionRequest): Result<Transaction, NetworkError>
    suspend fun delete(id: Int): Result<Unit, NetworkError>
    suspend fun getById(id: Int): Result<Transaction, NetworkError>
    suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>, NetworkError>
}