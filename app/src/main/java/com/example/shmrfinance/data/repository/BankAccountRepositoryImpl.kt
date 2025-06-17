package com.example.shmrfinance.data.repository

import com.example.network.core.NetworkRootError
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
    override suspend fun getAll(): Operation<List<BankAccount>, NetworkRootError> {
        return service.getAll()
    }

    override suspend fun getById(id: Int): Operation<BankAccount, NetworkRootError> {
        return service.getById(id)
    }

    override suspend fun create(request: AccountRequest): Operation<BankAccount, NetworkRootError> {
        return service.create(request)
    }

    override suspend fun update(id: Int, request: AccountRequest): Operation<BankAccount, NetworkRootError> {
        return service.update(id, request)
    }

    override suspend fun getUpdateHistory(id: Int): Operation<BankAccountHistoryResponse, NetworkRootError> {
        return service.getUpdateHistory(id)
    }
}