package com.example.shmrfinance.data.repository

import com.example.network.core.NetworkRootError
import com.example.network.core.Operation
import com.example.network.model.Transaction
import com.example.network.model.TransactionRequest
import com.example.network.serivce.TransactionService
import com.example.shmrfinance.domain.repository.TransactionRepository
import jakarta.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val service: TransactionService
): TransactionRepository {
    override suspend fun create(request: TransactionRequest): Operation<Transaction, NetworkRootError> {
        return service.create(request)
    }

    override suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Operation<Transaction, NetworkRootError> {
        return service.update(id, request)
    }

    override suspend fun delete(id: Int): Operation<Unit, NetworkRootError> {
        return service.delete(id)
    }

    override suspend fun getById(id: Int): Operation<Transaction, NetworkRootError> {
        return service.getById(id)
    }

    override suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Operation<List<Transaction>, NetworkRootError> {
        return service.getByPeriod(accountId, startDate, endDate)
    }
}