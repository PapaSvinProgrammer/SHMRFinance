package com.example.updatetransaction.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.category.GetAllCategory
import com.example.data.external.CategoryRepository
import com.example.data.external.TransactionRepository
import com.example.data.internal.di.DataModule
import com.example.localviewmodelfactory.ViewModelFactory
import com.example.localviewmodelfactory.ViewModelKey
import com.example.transaction.GetByIdTransaction
import com.example.transaction.UpdateTransaction
import com.example.updatetransaction.presentation.UpdateTransactionViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        DataModule::class
    ]
)
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
    }
}
