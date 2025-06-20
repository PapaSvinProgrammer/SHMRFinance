package com.example.data.repositoryImpl

import com.example.data.repository.TransactionRepository
import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.network.serivce.TransactionService
import jakarta.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val service: TransactionService
): TransactionRepository {
    override suspend fun create(request: TransactionRequest): Result<Transaction> {
        return service.create(request)
    }

    override suspend fun update(id: Int, request: TransactionRequest): Result<Transaction> {
        return service.update(id, request)
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return service.delete(id)
    }

    override suspend fun getById(id: Int): Result<Transaction> {
        return service.getById(id)
    }

    override suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>> {
        return service.getByPeriod(accountId, startDate, endDate)
    }
}