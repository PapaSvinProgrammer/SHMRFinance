package com.example.bankaccountlist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankaccountlist.presentation.BankAccountListViewModel
import com.example.bankaccountscreen.GetAllBankAccount
import com.example.data.external.remote.BankAccountRepository
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface BankAccountListModule {
    @Binds
    @BankAccountListScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @BankAccountListScope
    @ViewModelKey(BankAccountListViewModel::class)
    fun bindsBankAccountViewModel(viewModel: BankAccountListViewModel): ViewModel

    companion object {
        @Provides
        @BankAccountListScope
        fun providesGetAllBankAccounts(repository: BankAccountRepository): GetAllBankAccount {
            return GetAllBankAccount(repository)
        }
    }
}