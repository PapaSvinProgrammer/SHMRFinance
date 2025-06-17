package com.example.network.serivce

import com.example.network.model.BankAccount
import com.example.network.model.BankAccountHistoryResponse
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.model.AccountRequest

interface BankAccountService {
    suspend fun getAll(): Operation<List<BankAccount>, NetworkError>
    suspend fun getById(id: Int): Operation<BankAccount, NetworkError>
    suspend fun create(request: AccountRequest): Operation<BankAccount, NetworkError>
    suspend fun update(id: Int, request: AccountRequest): Operation<BankAccount, NetworkError>
    suspend fun getUpdateHistory(id: Int): Operation<BankAccountHistoryResponse, NetworkError>
}