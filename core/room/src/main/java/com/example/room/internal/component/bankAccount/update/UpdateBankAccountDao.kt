package com.example.room.internal.component.bankAccount.update

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface UpdateBankAccountDao {
    @Insert
    suspend fun insert()

    @Query("DELETE FROM update_bank_account WHERE bank_account_id = :id")
    suspend fun delete(id: Int)
}