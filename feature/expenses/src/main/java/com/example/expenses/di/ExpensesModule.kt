package com.example.expenses.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.local.TransactionRepositoryRoom
import com.example.data.external.remote.TransactionRepository
import com.example.expenses.presentation.ExpensesViewModel
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.transaction.GetTransactionByType
import com.example.transaction.SaveTransaction
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface ExpensesModule {
    @Binds
    @ExpensesScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ExpensesScope
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    fun bindsExpensesViewModel(viewModel: ExpensesViewModel): ViewModel

    companion object {
        @Provides
        @ExpensesScope
        fun providesGetTransactionByType(repository: TransactionRepository): GetTransactionByType {
            return GetTransactionByType(repository)
        }

        @Provides
        @ExpensesScope
        fun providesSaveTransaction(repository: TransactionRepositoryRoom): SaveTransaction {
            return SaveTransaction(repository)
        }
    }
}