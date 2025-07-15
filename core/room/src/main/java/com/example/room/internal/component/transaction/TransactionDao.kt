package com.example.room.internal.component.transaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
internal interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg entity: TransactionEntity)

    @Update
    suspend fun update(entity: TransactionEntity)

    @Query("DELETE FROM bank_transaction")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM bank_transaction WHERE transaction_id = :id")
    suspend fun getById(id: Int): TransactionResult?

    @Transaction
    @Query("SELECT * FROM bank_transaction")
    suspend fun getAll(): List<TransactionResult>
}