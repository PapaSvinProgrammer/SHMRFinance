package com.example.network.serviceImpl

import com.example.network.model.Transaction
import com.example.network.core.NetworkRootError
import com.example.network.core.Operation
import com.example.network.core.safeCall
import com.example.network.model.TransactionRequest
import com.example.network.serivce.TransactionService
import io.ktor.client.HttpClient
import io.ktor.util.network.UnresolvedAddressException
import jakarta.inject.Inject

class TransactionServiceImpl @Inject constructor(
    private val client: HttpClient
): TransactionService {
    override suspend fun create(request: TransactionRequest): Operation<Transaction, NetworkRootError> {
        TODO("Not yet implemented")
    }

    override suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Operation<Transaction, NetworkRootError> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int): Operation<Unit, NetworkRootError> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int): Operation<Transaction, NetworkRootError> {
        TODO("Not yet implemented")
    }

    override suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Operation<List<Transaction>, NetworkRootError> {
        return safeCall {
            throw UnresolvedAddressException()
        }
    }
}