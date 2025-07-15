package com.example.room.internal.component.bankAccount.delete

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface DeleteBankAccountDao {
    @Insert
    suspend fun insert(entity: DeleteBankAccount)

    @Query("DELETE FROM delete_bank_account WHERE bank_account_id = :id")
    suspend fun delete(id: Int)
}