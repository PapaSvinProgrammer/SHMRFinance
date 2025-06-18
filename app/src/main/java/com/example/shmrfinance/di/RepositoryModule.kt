package com.example.shmrfinance.di

import android.content.Context
import com.example.shmrfinance.data.repository.BankAccountRepositoryImpl
import com.example.shmrfinance.data.repository.CategoryRepositoryImpl
import com.example.shmrfinance.data.repository.PreferencesRepositoryImpl
import com.example.shmrfinance.data.repository.TransactionRepositoryImpl
import com.example.shmrfinance.domain.repository.BankAccountRepository
import com.example.shmrfinance.domain.repository.CategoryRepository
import com.example.shmrfinance.domain.repository.PreferencesRepository
import com.example.shmrfinance.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    companion object {
        @Provides
        @Singleton
        fun bindPreferencesRepositoryImpl(@ApplicationContext context: Context): PreferencesRepository {
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