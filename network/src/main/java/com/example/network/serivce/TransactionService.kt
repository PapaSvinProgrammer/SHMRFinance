package com.example.network.serivce

import com.example.core.Transaction
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.model.TransactionRequest

interface TransactionService {
    suspend fun create(request: TransactionRequest): Operation<Transaction, NetworkError>
    suspend fun update(id: Int, request: TransactionRequest): Operation<Transaction, NetworkError>
    suspend fun delete(id: Int): Operation<Unit, NetworkError>
    suspend fun getById(id: Int): Operation<Transaction, NetworkError>
    suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Operation<List<Transaction>, NetworkError>
}