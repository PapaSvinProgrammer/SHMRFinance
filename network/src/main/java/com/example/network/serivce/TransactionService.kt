package com.example.network.serivce

import com.example.network.model.Transaction
import com.example.network.core.NetworkRootError
import com.example.network.core.Operation
import com.example.network.model.TransactionRequest

interface TransactionService {
    suspend fun create(request: TransactionRequest): Operation<Transaction, NetworkRootError>
    suspend fun update(id: Int, request: TransactionRequest): Operation<Transaction, NetworkRootError>
    suspend fun delete(id: Int): Operation<Unit, NetworkRootError>
    suspend fun getById(id: Int): Operation<Transaction, NetworkRootError>
    suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Operation<List<Transaction>, NetworkRootError>
}