package com.example.data.internal.local

import com.example.data.external.local.BankAccountRepositoryRoom
import com.example.model.BankAccount
import com.example.room.external.BankAccountServiceRoom
import javax.inject.Inject

class BankAccountRepositoryRoomImpl @Inject constructor(
    private val service: BankAccountServiceRoom
) : BankAccountRepositoryRoom {
    override suspend fun insert(account: BankAccount): Result<Unit> {
        return service.insert(account)
    }

    override suspend fun insertAll(accounts: List<BankAccount>): Result<Unit> {
        return service.insertAll(accounts)
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return service.delete(id)
    }

    override suspend fun getById(id: Int): Result<BankAccount> {
        return service.getById(id)
    }

    override suspend fun getAll(): Result<List<BankAccount>> {
        return service.getAll()
    }
}
