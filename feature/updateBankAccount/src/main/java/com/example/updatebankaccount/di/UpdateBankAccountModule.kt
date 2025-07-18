package com.example.updatebankaccount.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankaccountscreen.DeleteBankAccount
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.bankaccountscreen.UpdateBankAccount
import com.example.data.external.remote.BankAccountRepository
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.updatebankaccount.presentation.UpdateBankAccountViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface UpdateBankAccountModule {
    @Binds
    @UpdateBankAccountScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @UpdateBankAccountScope
    @IntoMap
    @ViewModelKey(UpdateBankAccountViewModel::class)
    fun bindsExpensesViewModel(viewModel: UpdateBankAccountViewModel): ViewModel

    companion object {
        @Provides
        @UpdateBankAccountScope
        fun providesGetByIdBankAccount(repository: BankAccountRepository): GetByIdBankAccount {
            return GetByIdBankAccount(repository)
        }

        @Provides
        @UpdateBankAccountScope
        fun providesUpdateBankAccount(
            bankAccountRepository: BankAccountRepository
        ): UpdateBankAccount {
            return UpdateBankAccount(
                bankAccountRepository = bankAccountRepository
            )
        }

        @Provides
        @UpdateBankAccountScope
        fun providesDeleteBankAccount(
            bankAccountRepository: BankAccountRepository
        ): DeleteBankAccount {
            return DeleteBankAccount(
                bankAccountRepository = bankAccountRepository
            )
        }
    }
}