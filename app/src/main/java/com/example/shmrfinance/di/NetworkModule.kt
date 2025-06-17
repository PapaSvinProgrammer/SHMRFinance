package com.example.shmrfinance.di

import com.example.network.core.HttpClientFactory
import com.example.network.serivce.BankAccountService
import com.example.network.serivce.CategoryService
import com.example.network.serivce.TransactionService
import com.example.network.serviceImpl.BankAccountServiceImpl
import com.example.network.serviceImpl.CategoryServiceImpl
import com.example.network.serviceImpl.TransactionServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    companion object {
        @Provides
        @Singleton
        fun providesKtorClient(): HttpClient {
            return HttpClientFactory.create(OkHttp)
        }
    }

    @Binds
    @Singleton
    fun bindBankAccountServiceImpl(service: BankAccountServiceImpl): BankAccountService

    @Binds
    @Singleton
    fun bindCategoryServiceImpl(service: CategoryServiceImpl): CategoryService

    @Binds
    @Singleton
    fun bindTransactionServiceImpl(service: TransactionServiceImpl): TransactionService
}