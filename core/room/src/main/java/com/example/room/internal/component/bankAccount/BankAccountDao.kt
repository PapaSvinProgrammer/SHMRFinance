package com.example.room.internal.component.bankAccount

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface BankAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: BankAccountEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg entity: BankAccountEntity)

    @Query("DELETE FROM bank_account WHERE bank_account_id = :accountId")
    suspend fun delete(accountId: Int)

    @Query("SELECT * FROM bank_account WHERE bank_account_id = :accountId")
    suspend fun getById(accountId: Int): BankAccountEntity?

    @Query("SELECT * FROM bank_account")
    suspend fun getAll(): List<BankAccountEntity>
}
