package com.example.room.internal.component.transaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
internal interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(vararg entity: TransactionEntity)

    @Update
    suspend fun update(entity: TransactionEntity): Int

    @Query("DELETE FROM bank_transaction")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM bank_transaction WHERE transaction_id = :id")
    suspend fun getById(id: Int): TransactionResult?

    @Transaction
    @Query("SELECT * FROM bank_transaction")
    suspend fun getAll(): List<TransactionResult>

    @Transaction
    @Query("SELECT * FROM bank_transaction " +
            "WHERE account_id = :id " +
            "AND is_delete = 0 " +
            "AND transaction_date BETWEEN :start AND :end " +
            "ORDER BY transaction_date DESC")
    suspend fun getByPeriod(id: Int, start: Long, end: Long): List<TransactionResult>

    @Query("SELECT * FROM bank_transaction WHERE is_create = 1")
    suspend fun getOnlyCreated(): List<TransactionEntity>

    @Transaction
    @Query("SELECT * FROM bank_transaction WHERE is_update = 1")
    suspend fun getOnlyUpdated(): List<TransactionResult>

    @Query("SELECT * FROM bank_transaction WHERE is_delete = 1")
    suspend fun getOnlyDeleted(): List<TransactionEntity>
}