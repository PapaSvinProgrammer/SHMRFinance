package com.example.room.internal.di

import android.content.Context
import androidx.room.Room
import com.example.room.internal.AppDatabase
import com.example.room.internal.component.bankAccount.BankAccountDao
import com.example.room.internal.component.category.CategoryDao
import com.example.room.internal.component.transaction.TransactionDao
import com.example.utils.ApplicationScope
import dagger.Module
import dagger.Provides

private const val DATABASE_NAME = "shmr_finance_app_database"

@Module
internal interface RoomModuleImpl {
    companion object {
        @Provides
        @ApplicationScope
        fun providesAppDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

        @Provides
        @ApplicationScope
        fun providesTransactionDao(db: AppDatabase): TransactionDao {
            return db.transactionDao()
        }

        @Provides
        @ApplicationScope
        fun providesBankAccountDao(db: AppDatabase): BankAccountDao {
            return db.bankAccountDao()
        }

        @Provides
        @ApplicationScope
        fun providesCategoryDao(db: AppDatabase): CategoryDao {
            return db.categoryDao()
        }
    }
}