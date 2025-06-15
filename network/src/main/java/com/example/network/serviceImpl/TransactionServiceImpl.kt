package com.example.network.serviceImpl

import com.example.core.Transaction
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.model.TransactionRequest
import com.example.network.serivce.TransactionService

class TransactionServiceImpl: TransactionService {
    override suspend fun create(request: TransactionRequest): Operation<Transaction, NetworkError> {
        TODO("Not yet implemented")
    }

    override suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Operation<Transaction, NetworkError> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int): Operation<Unit, NetworkError> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int): Operation<Transaction, NetworkError> {
        TODO("Not yet implemented")
    }

    override suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Operation<List<Transaction>, NetworkError> {
        TODO("Not yet implemented")
    }
}