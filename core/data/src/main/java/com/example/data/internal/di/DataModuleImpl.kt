package com.example.data.internal.di

import android.content.Context
import com.example.utils.ApplicationScope
import com.example.data.external.BankAccountRepository
import com.example.data.external.CategoryRepository
import com.example.data.external.PreferencesRepository
import com.example.data.external.TransactionRepository
import com.example.data.external.TransactionRepositoryRoom
import com.example.data.internal.BankAccountRepositoryImpl
import com.example.data.internal.CategoryRepositoryImpl
import com.example.data.internal.PreferencesRepositoryImpl
import com.example.data.internal.TransactionRepositoryImpl
import com.example.data.internal.TransactionRepositoryRoomImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface DataModuleImpl {
    @Binds
    @ApplicationScope
    fun bindTransactionRepositoryImpl(repository: TransactionRepositoryImpl): TransactionRepository

    @Binds
    @ApplicationScope
    fun bindCategoryRepositoryImpl(repository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @ApplicationScope
    fun bindBankAccountRepositoryImpl(repository: BankAccountRepositoryImpl): BankAccountRepository

    @Binds
    @ApplicationScope
    fun bindsTransactionRepositoryRoom(repository: TransactionRepositoryRoomImpl): TransactionRepositoryRoom

    companion object {
        @Provides
        @ApplicationScope
        fun bindPreferencesRepositoryImpl(context: Context): PreferencesRepository {
            return PreferencesRepositoryImpl(context)
        }
    }
}