package com.example.shmrfinance.data.repository

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.model.AccountRequest
import com.example.network.model.BankAccount
import com.example.network.model.BankAccountHistoryResponse
import com.example.network.serivce.BankAccountService
import com.example.shmrfinance.domain.repository.BankAccountRepository
import jakarta.inject.Inject

class BankAccountRepositoryImpl @Inject constructor(
    private val service: BankAccountService
): BankAccountRepository {
    override suspend fun getAll(): Operation<List<BankAccount>, NetworkError> {
        return service.getAll()
    }

    override suspend fun getById(id: Int): Operation<BankAccount, NetworkError> {
        return service.getById(id)
    }

    override suspend fun create(request: AccountRequest): Operation<BankAccount, NetworkError> {
        return service.create(request)
    }

    override suspend fun update(id: Int, request: AccountRequest): Operation<BankAccount, NetworkError> {
        return service.update(id, request)
    }

    override suspend fun getUpdateHistory(id: Int): Operation<BankAccountHistoryResponse, NetworkError> {
        return service.getUpdateHistory(id)
    }
}