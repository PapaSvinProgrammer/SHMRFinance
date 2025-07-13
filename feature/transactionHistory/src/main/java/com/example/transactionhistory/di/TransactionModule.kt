package com.example.transactionhistory.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.TransactionRepository
import com.example.localviewmodelfactory.ViewModelFactory
import com.example.localviewmodelfactory.ViewModelKey
import com.example.transaction.GetTransactionByType
import com.example.transactionhistory.presentation.TransactionHistoryViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface TransactionModule {
    @Binds
    @TransactionScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @TransactionScope
    @IntoMap
    @ViewModelKey(TransactionHistoryViewModel::class)
    fun bindsExpensesViewModel(viewModel: TransactionHistoryViewModel): ViewModel

    companion object {
        @Provides
        @TransactionScope
        fun providesGetTransactionByType(repository: TransactionRepository): GetTransactionByType {
            return GetTransactionByType(repository)
        }
    }
}