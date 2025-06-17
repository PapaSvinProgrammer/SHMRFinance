package com.example.network.serivce

import com.example.network.model.BankAccount
import com.example.network.model.BankAccountHistoryResponse
import com.example.network.core.NetworkRootError
import com.example.network.core.Operation
import com.example.network.model.AccountRequest

interface BankAccountService {
    suspend fun getAll(): Operation<List<BankAccount>, NetworkRootError>
    suspend fun getById(id: Int): Operation<BankAccount, NetworkRootError>
    suspend fun create(request: AccountRequest): Operation<BankAccount, NetworkRootError>
    suspend fun update(id: Int, request: AccountRequest): Operation<BankAccount, NetworkRootError>
    suspend fun getUpdateHistory(id: Int): Operation<BankAccountHistoryResponse, NetworkRootError>
}