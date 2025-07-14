package com.example.createtransaction.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.category.GetAllCategory
import com.example.createtransaction.domain.FilterCategories
import com.example.createtransaction.presentation.CreateTransactionViewModel
import com.example.data.external.local.CategoryRepositoryRoom
import com.example.data.external.remote.BankAccountRepository
import com.example.data.external.remote.CategoryRepository
import com.example.data.external.remote.TransactionRepository
import com.example.localviewmodelfactory.ViewModelFactory
import com.example.localviewmodelfactory.ViewModelKey
import com.example.network.connectivityState.NetworkConnection
import com.example.transaction.CreateTransaction
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface CreateTransactionModule {
    @Binds
    @CreateTransactionScope
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @CreateTransactionScope
    @IntoMap
    @ViewModelKey(CreateTransactionViewModel::class)
    fun bindsCreateTransactionViewModel(viewModel: CreateTransactionViewModel): ViewModel

    companion object {
        @Provides
        @CreateTransactionScope
        fun providesGetByIdBankAccount(repository: BankAccountRepository): GetByIdBankAccount {
            return GetByIdBankAccount(repository)
        }

        @Provides
        @CreateTransactionScope
        fun providesGetAllCategory(
            categoryRepository: CategoryRepository,
            categoryRepositoryRoom: CategoryRepositoryRoom,
            networkConnection: NetworkConnection
        ): GetAllCategory {
            return GetAllCategory(
                categoryRepository = categoryRepository,
                categoryRepositoryRoom = categoryRepositoryRoom,
                networkConnection = networkConnection
            )
        }

        @Provides
        @CreateTransactionScope
        fun providesFilterCategories(): FilterCategories {
            return FilterCategories()
        }

        @Provides
        @CreateTransactionScope
        fun providesCreateTransaction(repository: TransactionRepository): CreateTransaction {
            return CreateTransaction(repository)
        }
    }
}