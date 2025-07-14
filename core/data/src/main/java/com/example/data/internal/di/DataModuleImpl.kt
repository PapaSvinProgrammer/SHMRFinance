package com.example.data.internal.di

import android.content.Context
import com.example.utils.ApplicationScope
import com.example.data.external.remote.BankAccountRepository
import com.example.data.external.remote.CategoryRepository
import com.example.data.external.remote.PreferencesRepository
import com.example.data.external.remote.TransactionRepository
import com.example.data.external.local.TransactionRepositoryRoom
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
    fun bindsTransactionRepositoryRoom(repository: TransactionRepositoryRoomImpl): TransactionRepositoryRoom

    companion object {
        @Provides
        @ApplicationScope
        fun bindPreferencesRepositoryImpl(context: Context): PreferencesRepository {
            return PreferencesRepositoryImpl(context)
        }
    }
}