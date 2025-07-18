package com.example.data.internal.remote

import com.example.data.external.remote.BankAccountRepository
import com.example.model.AccountRequest
import com.example.model.BankAccount
import com.example.model.BankAccountHistoryResponse
import com.example.network.connectivityState.NetworkConnection
import com.example.network.external.BankAccountService
import com.example.room.external.BankAccountServiceRoom
import javax.inject.Inject

internal class BankAccountRepositoryImpl @Inject constructor(
    private val serviceRemote: BankAccountService,
    private val serviceLocal: BankAccountServiceRoom,
    private val networkConnection: NetworkConnection
): BankAccountRepository {
    override suspend fun getAll(): Result<List<BankAccount>> {
        if (networkConnection.isOnline()) {
            return serviceRemote.getAll()
        }

        return serviceLocal.getAll()
    }

    override suspend fun getById(id: Int): Result<BankAccount> {
        if (networkConnection.isOnline()) {
            return serviceRemote.getById(id)
        }

        return serviceLocal.getById(id)
    }

    override suspend fun create(request: AccountRequest): Result<BankAccount> {
        return serviceRemote.create(request)
    }

    override suspend fun update(id: Int, request: AccountRequest): Result<BankAccount> {
        return serviceRemote.update(id, request)
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return serviceRemote.delete(id)
    }

    override suspend fun getUpdateHistory(id: Int): Result<BankAccountHistoryResponse> {
        return serviceRemote.getUpdateHistory(id)
    }
}