package com.example.room.internal.di

import android.content.Context
import androidx.room.Room
import com.example.room.external.BankAccountServiceRoom
import com.example.room.external.CategoryServiceRoom
import com.example.room.external.TransactionServiceRoom
import com.example.room.internal.AppDatabase
import com.example.room.internal.component.bankAccount.BankAccountDao
import com.example.room.internal.component.category.CategoryDao
import com.example.room.internal.component.transaction.TransactionDao
import com.example.room.internal.service.BankAccountServiceRoomImpl
import com.example.room.internal.service.CategoryServiceRoomImpl
import com.example.room.internal.service.TransactionServiceRoomImpl
import com.example.utils.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.Provides

private const val DATABASE_NAME = "shmr_finance_app_database"

@Module
internal interface RoomModuleImpl {
    @Binds
    @ApplicationScope
    fun bindsBankAccountServiceRoomImpl(service: BankAccountServiceRoomImpl): BankAccountServiceRoom

    @Binds
    @ApplicationScope
    fun bindsCategoryServiceRoomImpl(service: CategoryServiceRoomImpl): CategoryServiceRoom

    @Binds
    @ApplicationScope
    fun bindsTransactionServiceRoomImpl(service: TransactionServiceRoomImpl): TransactionServiceRoom

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