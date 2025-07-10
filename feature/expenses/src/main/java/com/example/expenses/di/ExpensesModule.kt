package com.example.expenses.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.TransactionRepository
import com.example.data.internal.di.DataModule
import com.example.expenses.presentation.ExpensesViewModel
import com.example.localviewmodelfactory.ViewModelFactory
import com.example.localviewmodelfactory.ViewModelKey
import com.example.transaction.GetTransactionByType
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [DataModule::class]
)
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
    }
}