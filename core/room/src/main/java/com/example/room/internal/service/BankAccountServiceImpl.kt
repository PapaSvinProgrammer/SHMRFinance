package com.example.room.internal.service

import com.example.model.BankAccount
import com.example.room.external.BankAccountService
import com.example.room.internal.component.bankAccount.BankAccountDao
import com.example.room.internal.mapper.toDomain
import com.example.room.internal.mapper.toEntity
import com.example.room.internal.safeCall
import javax.inject.Inject

internal class BankAccountServiceImpl @Inject constructor(
    private val dao: BankAccountDao
): BankAccountService {
    override suspend fun insert(account: BankAccount): Result<Unit> {
        return safeCall {
            dao.insert(account.toEntity())
        }
    }

    override suspend fun insertAll(accounts: List<BankAccount>): Result<Unit> {
        return safeCall {
            val list = accounts.toEntity().toTypedArray()
            dao.insertAll(*list)
        }
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return safeCall {
            dao.delete(id)
        }
    }

    override suspend fun getById(id: Int): Result<BankAccount> {
        return safeCall {
            dao.getById(id)
        }.map { it.toDomain() }
    }

    override suspend fun getAll(): Result<List<BankAccount>> {
        return safeCall {
            dao.getAll()
        }.map { it.toDomain() }
    }
}
