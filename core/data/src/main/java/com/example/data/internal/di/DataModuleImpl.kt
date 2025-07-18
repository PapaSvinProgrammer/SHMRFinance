package com.example.data.internal.di

import android.content.Context
import com.example.data.external.local.BankAccountRepositoryRoom
import com.example.data.external.local.CategoryRepositoryRoom
import com.example.utils.ApplicationScope
import com.example.data.external.remote.BankAccountRepository
import com.example.data.external.remote.CategoryRepository
import com.example.data.external.remote.PreferencesRepository
import com.example.data.external.remote.TransactionRepository
import com.example.data.external.local.TransactionRepositoryRoom
import com.example.data.internal.local.BankAccountRepositoryRoomImpl
import com.example.data.internal.local.CategoryRepositoryRoomImpl
import com.example.data.internal.remote.BankAccountRepositoryImpl
import com.example.data.internal.remote.CategoryRepositoryImpl
import com.example.data.internal.remote.PreferencesRepositoryImpl
import com.example.data.internal.remote.TransactionRepositoryImpl
import com.example.data.internal.local.TransactionRepositoryRoomImpl
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
    fun bindsTransactionRepositoryRoomImpl(repository: TransactionRepositoryRoomImpl): TransactionRepositoryRoom

    @Binds
    @ApplicationScope
    fun bindsCategoryRepositoryRoomImpl(repository: CategoryRepositoryRoomImpl): CategoryRepositoryRoom

    @Binds
    @ApplicationScope
    fun bindsBankAccountRepositoryRoomImpl(repository: BankAccountRepositoryRoomImpl): BankAccountRepositoryRoom

    companion object {
        @Provides
        @ApplicationScope
        fun bindPreferencesRepositoryImpl(context: Context): PreferencesRepository {
            return PreferencesRepositoryImpl(context)
        }
    }
}