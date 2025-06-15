package com.example.network.serviceImpl

import com.example.core.BankAccount
import com.example.core.BankAccountHistoryResponse
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.model.AccountRequest
import com.example.network.serivce.BankAccountService

class BankAccountImpl: BankAccountService {
    override suspend fun getAll(): Operation<List<BankAccount>, NetworkError> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int): Operation<BankAccount, NetworkError> {
        TODO("Not yet implemented")
    }

    override suspend fun create(request: AccountRequest): Operation<BankAccount, NetworkError> {
        TODO("Not yet implemented")
    }

    override suspend fun update(
        id: Int,
        request: AccountRequest
    ): Operation<BankAccount, NetworkError> {
        TODO("Not yet implemented")
    }

    override suspend fun getUpdateHistory(id: Int): Operation<BankAccountHistoryResponse, NetworkError> {
        TODO("Not yet implemented")
    }
}