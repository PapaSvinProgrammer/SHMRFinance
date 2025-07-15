package com.example.updatetransaction.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.category.GetAllCategory
import com.example.data.external.local.BankAccountRepositoryRoom
import com.example.data.external.local.CategoryRepositoryRoom
import com.example.data.external.local.TransactionRepositoryRoom
import com.example.data.external.remote.BankAccountRepository
import com.example.data.external.remote.CategoryRepository
import com.example.data.external.remote.TransactionRepository
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.network.connectivityState.NetworkConnection
import com.example.transaction.GetByIdTransaction
import com.example.transaction.UpdateTransaction
import com.example.updatetransaction.presentation.UpdateTransactionViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface UpdateTransactionModule {
    @Binds
    @UpdateTransactionScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @UpdateTransactionScope
    @IntoMap
    @ViewModelKey(UpdateTransactionViewModel::class)
    fun bindsViewModel(viewModel: UpdateTransactionViewModel): ViewModel

    companion object {
        @Provides
        @UpdateTransactionScope
        fun providesUpdateTransaction(
            transactionRepository: TransactionRepository,
            transactionRepositoryRoom: TransactionRepositoryRoom,
            networkConnection: NetworkConnection
        ): UpdateTransaction {
            return UpdateTransaction(
                transactionRepository = transactionRepository,
                transactionRepositoryRoom = transactionRepositoryRoom,
                networkConnection = networkConnection
            )
        }

        @Provides
        @UpdateTransactionScope
        fun providesGetByIdTransaction(
            transactionRepository: TransactionRepository,
            transactionRepositoryRoom: TransactionRepositoryRoom,
            networkConnection: NetworkConnection
        ): GetByIdTransaction {
            return GetByIdTransaction(
                transactionRepository = transactionRepository,
                transactionRepositoryRoom = transactionRepositoryRoom,
                networkConnection = networkConnection
            )
        }

        @Provides
        @UpdateTransactionScope
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
        @UpdateTransactionScope
        fun providesGetByIdBankAccount(
            bankAccountRepository: BankAccountRepository,
            bankAccountRepositoryRoom: BankAccountRepositoryRoom,
            connection: NetworkConnection
        ): GetByIdBankAccount {
            return GetByIdBankAccount(
                bankAccountRepository = bankAccountRepository,
                bankAccountRepositoryRoom = bankAccountRepositoryRoom,
                networkConnection = connection
            )
        }
    }
}
