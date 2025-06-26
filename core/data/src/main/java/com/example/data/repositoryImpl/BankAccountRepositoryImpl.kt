package com.example.data.repositoryImpl

import com.example.data.repository.BankAccountRepository
import com.example.model.AccountRequest
import com.example.model.BankAccount
import com.example.model.BankAccountHistoryResponse
import com.example.network.serivce.BankAccountService
import javax.inject.Inject

class BankAccountRepositoryImpl @Inject constructor(
    private val service: BankAccountService
): BankAccountRepository {
    override suspend fun getAll(): Result<List<BankAccount>> {
        return service.getAll()
    }

    override suspend fun getById(id: Int): Result<BankAccount> {
        return service.getById(id)
    }

    override suspend fun create(request: AccountRequest): Result<BankAccount> {
        return service.create(request)
    }

    override suspend fun update(id: Int, request: AccountRequest): Result<BankAccount> {
        return service.update(id, request)
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return service.delete(id)
    }

    override suspend fun getUpdateHistory(id: Int): Result<BankAccountHistoryResponse> {
        return service.getUpdateHistory(id)
    }
}