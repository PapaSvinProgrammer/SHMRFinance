package com.example.data.internal.di

import android.content.Context
import com.example.data.external.BankAccountRepository
import com.example.data.external.CategoryRepository
import com.example.data.external.PreferencesRepository
import com.example.data.external.TransactionRepository
import com.example.data.internal.BankAccountRepositoryImpl
import com.example.data.internal.CategoryRepositoryImpl
import com.example.data.internal.PreferencesRepositoryImpl
import com.example.data.internal.TransactionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal interface DataModuleImpl {
    companion object {
        @Provides
        fun bindPreferencesRepositoryImpl(context: Context): PreferencesRepository {
            return PreferencesRepositoryImpl(context)
        }
    }

    @Binds
    fun bindTransactionRepositoryImpl(repository: TransactionRepositoryImpl): TransactionRepository

    @Binds
    fun bindCategoryRepositoryImpl(repository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindBankAccountRepositoryImpl(repository: BankAccountRepositoryImpl): BankAccountRepository
}