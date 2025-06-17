package com.example.shmrfinance.domain.repository

import com.example.network.core.NetworkRootError
import com.example.network.core.Operation
import com.example.network.model.AccountRequest
import com.example.network.model.BankAccount
import com.example.network.model.BankAccountHistoryResponse

interface BankAccountRepository {
    suspend fun getAll(): Operation<List<BankAccount>, NetworkRootError>
    suspend fun getById(id: Int): Operation<BankAccount, NetworkRootError>
    suspend fun create(request: AccountRequest): Operation<BankAccount, NetworkRootError>
    suspend fun update(id: Int, request: AccountRequest): Operation<BankAccount, NetworkRootError>
    suspend fun getUpdateHistory(id: Int): Operation<BankAccountHistoryResponse, NetworkRootError>
}