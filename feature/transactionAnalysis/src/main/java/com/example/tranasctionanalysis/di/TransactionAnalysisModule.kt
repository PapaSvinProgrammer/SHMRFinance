package com.example.tranasctionanalysis.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.tranasctionanalysis.presentation.TransactionAnalysisViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface TransactionAnalysisModule {
    @Binds
    @TransactionAnalysisScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @TransactionAnalysisScope
    @IntoMap
    @ViewModelKey(TransactionAnalysisViewModel::class)
    fun bindsViewModel(viewModel: TransactionAnalysisViewModel): ViewModel

    companion object {

    }
}