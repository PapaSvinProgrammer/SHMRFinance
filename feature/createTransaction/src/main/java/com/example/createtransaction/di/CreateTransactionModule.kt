package com.example.createtransaction.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.createtransaction.presentation.CreateTransactionViewModel
import com.example.data.internal.di.DataModule
import com.example.localviewmodelfactory.ViewModelFactory
import com.example.localviewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [DataModule::class]
)
internal interface CreateTransactionModule {
    @Binds
    @CreateTransactionScope
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @CreateTransactionScope
    @IntoMap
    @ViewModelKey(CreateTransactionViewModel::class)
    fun bindsCreateTransactionViewModel(viewModel: CreateTransactionViewModel): ViewModel
}