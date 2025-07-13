package com.example.room.transaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(entity: TransactionEntity)

    @Query("DELETE FROM bank_transaction WHERE transaction_id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM bank_transaction WHERE transaction_id = :id")
    suspend fun getById(id: Int): TransactionEntity

    @Query("SELECT * FROM bank_transaction")
    suspend fun getAll(): List<TransactionEntity>
}