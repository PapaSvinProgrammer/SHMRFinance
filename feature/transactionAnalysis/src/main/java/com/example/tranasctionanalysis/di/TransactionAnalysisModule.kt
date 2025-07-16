package com.example.tranasctionanalysis.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.local.TransactionRepositoryRoom
import com.example.data.external.remote.TransactionRepository
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.network.connectivityState.NetworkConnection
import com.example.tranasctionanalysis.presentation.TransactionAnalysisViewModel
import com.example.transaction.GetTransactionByType
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface TransactionAnalysisModule {
    @Binds
    @TransactionAnalysisScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @TransactionAnalysisScope
    @IntoMap
    @ViewModelKey(TransactionAnalysisViewModel::class)
    fun bindsViewModel(viewModel: TransactionAnalysisViewModel): ViewModel

    companion object {
        @Provides
        @TransactionAnalysisScope
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
    }
}