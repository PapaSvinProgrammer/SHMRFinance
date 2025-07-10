package com.example.createbankaccount.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankaccountscreen.CreateBankAccount
import com.example.createbankaccount.presentation.CreateBankAccountViewModel
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
internal interface CreateBankAccountModule {
    @Binds
    @CreateBankAccountScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @CreateBankAccountScope
    @ViewModelKey(CreateBankAccountViewModel::class)
    fun bindsCreateBankAccountViewmodel(viewMode: CreateBankAccountViewModel): ViewModel

    companion object {
        @Provides
        @CreateBankAccountScope
        fun providesCreateBankAccount(repository: BankAccountRepository): CreateBankAccount {
            return CreateBankAccount(repository)
        }
    }
}