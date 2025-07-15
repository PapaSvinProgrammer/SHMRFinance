package com.example.room.internal.component.bankAccount.create

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface CreateBankAccountDao {
    @Insert
    suspend fun insert(entity: CreateBankAccountEntity)

    @Query("DELETE FROM create_bank_account WHERE id = :tableId")
    suspend fun delete(tableId: Int)
}