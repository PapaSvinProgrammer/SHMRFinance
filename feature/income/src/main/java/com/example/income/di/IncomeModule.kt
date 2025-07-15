package com.example.income.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.local.TransactionRepositoryRoom
import com.example.data.external.remote.TransactionRepository
import com.example.income.presentation.IncomeViewModel
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.network.connectivityState.NetworkConnection
import com.example.transaction.GetTransactionByType
import com.example.transaction.SaveTransaction
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface IncomeModule {
    @Binds
    @IncomeScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IncomeScope
    @IntoMap
    @ViewModelKey(IncomeViewModel::class)
    fun bindsExpensesViewModel(viewModel: IncomeViewModel): ViewModel

    companion object {
        @Provides
        @IncomeScope
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
        @IncomeScope
        fun providesSaveTransaction(repository: TransactionRepositoryRoom): SaveTransaction {
            return SaveTransaction(repository)
        }
    }
}