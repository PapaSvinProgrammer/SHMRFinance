package com.example.transactionhistory.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.local.TransactionRepositoryRoom
import com.example.data.external.remote.TransactionRepository
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.network.connectivityState.NetworkConnection
import com.example.transaction.GetTransactionByType
import com.example.transaction.SaveTransaction
import com.example.transactionhistory.presentation.TransactionHistoryViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface TransactionModule {
    @Binds
    @TransactionHistoryScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @TransactionHistoryScope
    @IntoMap
    @ViewModelKey(TransactionHistoryViewModel::class)
    fun bindsExpensesViewModel(viewModel: TransactionHistoryViewModel): ViewModel

    companion object {
        @Provides
        @TransactionHistoryScope
        fun providesGetTransactionByType(
            transactionRepository: TransactionRepository,
            transactionRepositoryRoom: TransactionRepositoryRoom,
            networkConnection: NetworkConnection
        ): GetTransactionByType {
            return GetTransactionByType(
                transactionRepository = transactionRepository,
                transactionRepositoryRoom = transactionRepositoryRoom,
                networkConnection = networkConnection
            )
        }

        @Provides
        @TransactionHistoryScope
        fun providesSaveTransaction(repository: TransactionRepositoryRoom): SaveTransaction {
            return SaveTransaction(repository)
        }
    }
}