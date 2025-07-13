package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.bankAccount.BankAccountDao
import com.example.room.bankAccount.BankAccountEntity
import com.example.room.category.CategoryDao
import com.example.room.category.CategoryEntity
import com.example.room.transaction.TransactionDao
import com.example.room.transaction.TransactionEntity

@Database(
    entities = [
        BankAccountEntity::class,
        CategoryEntity::class,
        TransactionEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bankAccountDao(): BankAccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
}