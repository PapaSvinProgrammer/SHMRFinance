package com.example.data.external.local

import com.example.model.BankAccount

interface BankAccountRepositoryRoom {
    suspend fun insert(account: BankAccount): Result<Unit>
    suspend fun insertAll(accounts: List<BankAccount>): Result<Unit>
    suspend fun delete(id: Int): Result<Unit>
    suspend fun getById(id: Int): Result<BankAccount>
    suspend fun getAll(): Result<List<BankAccount>>
}