package com.example.bankaccountscreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankaccountscreen.GetAllBankAccount
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.bankaccountscreen.presentation.BankAccountViewModel
import com.example.data.external.local.BankAccountRepositoryRoom
import com.example.data.external.remote.BankAccountRepository
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.network.connectivityState.NetworkConnection
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
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
        fun providesGetAllBankAccounts(
            bankAccountRepository: BankAccountRepository,
            bankAccountRepositoryRoom: BankAccountRepositoryRoom,
            connection: NetworkConnection
        ): GetAllBankAccount {
            return GetAllBankAccount(
                bankAccountRepository = bankAccountRepository,
                bankAccountRepositoryRoom = bankAccountRepositoryRoom,
                networkConnection = connection
            )
        }

        @Provides
        @BankAccountScope
        fun providesGetByIdBankAccount(
            bankAccountRepository: BankAccountRepository,
            bankAccountRepositoryRoom: BankAccountRepositoryRoom,
            connection: NetworkConnection
        ): GetByIdBankAccount {
            return GetByIdBankAccount(
                bankAccountRepository = bankAccountRepository,
                bankAccountRepositoryRoom = bankAccountRepositoryRoom,
                networkConnection = connection
            )
        }
    }
}