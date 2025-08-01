package com.example.updatetransaction.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.category.GetAllCategory
import com.example.data.external.remote.BankAccountRepository
import com.example.data.external.remote.CategoryRepository
import com.example.data.external.remote.TransactionRepository
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.transaction.GetByIdTransaction
import com.example.transaction.UpdateTransaction
import com.example.updatetransaction.presentation.UpdateTransactionViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface UpdateTransactionModule {
    @Binds
    @UpdateTransactionScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @UpdateTransactionScope
    @IntoMap
    @ViewModelKey(UpdateTransactionViewModel::class)
    fun bindsViewModel(viewModel: UpdateTransactionViewModel): ViewModel

    companion object {
        @Provides
        @UpdateTransactionScope
        fun providesUpdateTransaction(repository: TransactionRepository): UpdateTransaction {
            return UpdateTransaction(repository)
        }

        @Provides
        @UpdateTransactionScope
        fun providesGetByIdTransaction(repository: TransactionRepository): GetByIdTransaction {
            return GetByIdTransaction(repository)
        }

        @Provides
        @UpdateTransactionScope
        fun providesGetAllCategory(repository: CategoryRepository): GetAllCategory {
            return GetAllCategory(repository)
        }

        @Provides
        @UpdateTransactionScope
        fun providesGetByIdBankAccount(repository: BankAccountRepository): GetByIdBankAccount {
            return GetByIdBankAccount(repository)
        }
    }
}
