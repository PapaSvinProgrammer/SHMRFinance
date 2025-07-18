package com.example.room.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.internal.component.bankAccount.BankAccountDao
import com.example.room.internal.component.bankAccount.BankAccountEntity
import com.example.room.internal.component.category.CategoryDao
import com.example.room.internal.component.category.CategoryEntity
import com.example.room.internal.component.transaction.TransactionDao
import com.example.room.internal.component.transaction.TransactionEntity
import com.example.room.internal.component.workLog.WorkLogDao
import com.example.room.internal.component.workLog.WorkLogEntity

@Database(
    entities = [
        BankAccountEntity::class,
        CategoryEntity::class,
        TransactionEntity::class,
        WorkLogEntity::class
    ],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun bankAccountDao(): BankAccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
    abstract fun workLogDao(): WorkLogDao
}