package com.example.room.internal.component.bankAccount

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface BankAccountDao {
    @Insert
    suspend fun insert(entity: BankAccountEntity)

    @Insert
    suspend fun insertAll(vararg entity: BankAccountEntity)

    @Query("DELETE FROM bank_account WHERE bank_account_id = :accountId")
    suspend fun delete(accountId: Int)

    @Query("SELECT * FROM bank_account WHERE bank_account_id = :accountId")
    suspend fun getById(accountId: Int): BankAccountEntity

    @Query("SELECT * FROM bank_account")
    suspend fun getAll(): List<BankAccountEntity>
}
