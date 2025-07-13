package com.example.room.di

import android.content.Context
import androidx.room.Room
import com.example.utils.ApplicationScope
import com.example.room.AppDatabase
import com.example.room.bankAccount.BankAccountDao
import com.example.room.category.CategoryDao
import com.example.room.transaction.TransactionDao
import dagger.Module
import dagger.Provides

private const val DATABASE_NAME = "shmr_finance_app_database"

@Module
interface RoomModule {
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
