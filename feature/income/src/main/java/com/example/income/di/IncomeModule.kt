package com.example.income.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.TransactionRepository
import com.example.income.presentation.IncomeViewModel
import com.example.localviewmodelfactory.ViewModelFactory
import com.example.localviewmodelfactory.ViewModelKey
import com.example.transaction.GetTransactionByType
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
        fun providesGetTransactionByType(repository: TransactionRepository): GetTransactionByType {
            return GetTransactionByType(repository)
        }
    }
}