package com.example.room.bankAccount

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BankAccountDao {
    @Insert
    suspend fun insert(entity: BankAccountEntity)

    @Query("DELETE FROM bank_account WHERE bank_account_id = :accountId")
    suspend fun delete(accountId: Int)

    @Query("SELECT * FROM bank_account WHERE bank_account_id = :accountId")
    suspend fun getById(accountId: Int): BankAccountEntity

    @Query("SELECT * FROM bank_account")
    suspend fun getAll(): List<BankAccountEntity>
}
