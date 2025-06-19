package com.example.network.serivce

import com.example.common.NetworkError
import com.example.common.Result
import com.example.model.AccountRequest
import com.example.model.BankAccount
import com.example.model.BankAccountHistoryResponse

interface BankAccountService {
    suspend fun getAll(): Result<List<BankAccount>, NetworkError>
    suspend fun getById(id: Int): Result<BankAccount, NetworkError>
    suspend fun create(request: AccountRequest): Result<BankAccount, NetworkError>
    suspend fun update(id: Int, request: AccountRequest): Result<BankAccount, NetworkError>
    suspend fun delete(id: Int): Result<Unit, NetworkError>
    suspend fun getUpdateHistory(id: Int): Result<BankAccountHistoryResponse, NetworkError>
}