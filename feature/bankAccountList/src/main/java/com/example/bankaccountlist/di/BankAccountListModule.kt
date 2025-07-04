package com.example.bankaccountlist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankaccountlist.BankAccountListViewModel
import com.example.data.internal.di.DataModule
import com.example.localviewmodelfactory.ViewModelFactory
import com.example.localviewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        DataModule::class
    ]
)
internal interface BankAccountListModule {
    @Binds
    @BankAccountListScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @BankAccountListScope
    @IntoMap
    @ViewModelKey(BankAccountListViewModel::class)
    fun bindsBankAccountViewModel(viewModel: BankAccountListViewModel): ViewModel
}