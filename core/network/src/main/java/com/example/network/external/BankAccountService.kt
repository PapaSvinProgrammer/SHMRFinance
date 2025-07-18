package com.example.network.external

import com.example.model.AccountRequest
import com.example.model.BankAccount
import com.example.model.BankAccountHistoryResponse

interface BankAccountService {
    suspend fun getAll(): Result<List<BankAccount>>
    suspend fun getById(id: Int): Result<BankAccount>
    suspend fun create(request: AccountRequest): Result<BankAccount>
    suspend fun update(id: Int, request: AccountRequest): Result<BankAccount>
    suspend fun delete(id: Int): Result<Unit>
    suspend fun getUpdateHistory(id: Int): Result<BankAccountHistoryResponse>
}