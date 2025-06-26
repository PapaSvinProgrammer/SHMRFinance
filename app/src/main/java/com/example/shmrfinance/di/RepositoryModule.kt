package com.example.shmrfinance.di

import android.content.Context
import com.example.data.repository.BankAccountRepository
import com.example.data.repository.CategoryRepository
import com.example.data.repository.PreferencesRepository
import com.example.data.repository.TransactionRepository
import com.example.data.repositoryImpl.BankAccountRepositoryImpl
import com.example.data.repositoryImpl.CategoryRepositoryImpl
import com.example.data.repositoryImpl.PreferencesRepositoryImpl
import com.example.data.repositoryImpl.TransactionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface RepositoryModule {
    companion object {
        @Provides
        @Singleton
        fun bindPreferencesRepositoryImpl(context: Context): PreferencesRepository {
            return PreferencesRepositoryImpl(context)
        }
    }

    @Binds
    @Singleton
    fun bindTransactionRepositoryImpl(repository: TransactionRepositoryImpl): TransactionRepository

    @Binds
    @Singleton
    fun bindCategoryRepositoryImpl(repository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    fun bindBankAccountRepositoryImpl(repository: BankAccountRepositoryImpl): BankAccountRepository
}