package com.example.data.repositoryImpl

import com.example.common.NetworkError
import com.example.common.Result
import com.example.data.repository.BankAccountRepository
import com.example.model.AccountRequest
import com.example.model.BankAccount
import com.example.model.BankAccountHistoryResponse
import com.example.network.serivce.BankAccountService
import jakarta.inject.Inject

class BankAccountRepositoryImpl @Inject constructor(
    private val service: BankAccountService
): BankAccountRepository {
    override suspend fun getAll(): Result<List<BankAccount>, NetworkError> {
        return service.getAll()
    }

    override suspend fun getById(id: Int): Result<BankAccount, NetworkError> {
        return service.getById(id)
    }

    override suspend fun create(request: AccountRequest): Result<BankAccount, NetworkError> {
        return service.create(request)
    }

    override suspend fun update(id: Int, request: AccountRequest): Result<BankAccount, NetworkError> {
        return service.update(id, request)
    }

    override suspend fun delete(id: Int): Result<Unit, NetworkError> {
        return service.delete(id)
    }

    override suspend fun getUpdateHistory(id: Int): Result<BankAccountHistoryResponse, NetworkError> {
        return service.getUpdateHistory(id)
    }
}