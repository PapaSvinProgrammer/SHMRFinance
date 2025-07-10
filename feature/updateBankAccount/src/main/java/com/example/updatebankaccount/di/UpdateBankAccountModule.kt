package com.example.updatebankaccount.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankaccountscreen.DeleteBankAccount
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.bankaccountscreen.UpdateBankAccount
import com.example.data.external.BankAccountRepository
import com.example.data.internal.di.DataModule
import com.example.localviewmodelfactory.ViewModelFactory
import com.example.localviewmodelfactory.ViewModelKey
import com.example.updatebankaccount.presentation.UpdateBankAccountViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [DataModule::class]
)
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
        fun providesUpdateBankAccount(repository: BankAccountRepository): UpdateBankAccount {
            return UpdateBankAccount(repository)
        }

        @Provides
        @UpdateBankAccountScope
        fun providesDeleteBankAccount(repository: BankAccountRepository): DeleteBankAccount {
            return DeleteBankAccount(repository)
        }
    }
}