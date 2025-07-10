package com.example.bankaccountscreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankaccountscreen.presentation.BankAccountViewModel
import com.example.bankaccountscreen.GetAllBankAccount
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.data.external.BankAccountRepository
import com.example.data.internal.di.DataModule
import com.example.localviewmodelfactory.ViewModelFactory
import com.example.localviewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [DataModule::class]
)
internal interface BankAccountModule {
    @Binds
    @BankAccountScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @BankAccountScope
    @ViewModelKey(BankAccountViewModel::class)
    fun bindsBankAccountViewModel(viewModel: BankAccountViewModel): ViewModel

    companion object {
        @Provides
        @BankAccountScope
        fun providesGetAllBankAccounts(repository: BankAccountRepository): GetAllBankAccount {
            return GetAllBankAccount(repository)
        }

        @Provides
        @BankAccountScope
        fun providesGetByIdBankAccount(repository: BankAccountRepository): GetByIdBankAccount {
            return GetByIdBankAccount(repository)
        }
    }
}