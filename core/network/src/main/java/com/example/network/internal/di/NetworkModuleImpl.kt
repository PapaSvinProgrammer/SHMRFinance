package com.example.network.internal.di

import com.example.utils.ApplicationScope
import com.example.network.internal.common.HttpClientFactory
import com.example.network.external.BankAccountService
import com.example.network.external.CategoryService
import com.example.network.external.TransactionService
import com.example.network.internal.service.BankAccountServiceImpl
import com.example.network.internal.service.CategoryServiceImpl
import com.example.network.internal.service.TransactionServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

@Module
internal interface NetworkModuleImpl {
    @Binds
    @ApplicationScope
    fun bindBankAccountServiceImpl(service: BankAccountServiceImpl): BankAccountService

    @Binds
    @ApplicationScope
    fun bindCategoryServiceImpl(service: CategoryServiceImpl): CategoryService

    @Binds
    @ApplicationScope
    fun bindTransactionServiceImpl(service: TransactionServiceImpl): TransactionService

    companion object {
        @Provides
        @ApplicationScope
        fun providesKtorClient(): HttpClient {
            return HttpClientFactory.create(OkHttp)
        }
    }
}
