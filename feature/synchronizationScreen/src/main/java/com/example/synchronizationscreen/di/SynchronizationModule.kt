package com.example.synchronizationscreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.synchronizationscreen.presentation.SynchronizationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface SynchronizationModule {
    @Binds
    @SynchronizationScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @SynchronizationScope
    @IntoMap
    @ViewModelKey(SynchronizationViewModel::class)
    fun bindsViewModel(viewModel: SynchronizationViewModel): ViewModel
}