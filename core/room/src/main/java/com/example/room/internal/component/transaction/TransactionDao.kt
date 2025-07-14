package com.example.room.internal.component.transaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface TransactionDao {
    @Insert
    suspend fun insert(entity: TransactionEntity)

    @Query("DELETE FROM bank_transaction WHERE transaction_id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM bank_transaction WHERE transaction_id = :id")
    suspend fun getById(id: Int): TransactionEntity

    @Query("SELECT * FROM bank_transaction")
    suspend fun getAll(): List<TransactionEntity>
}