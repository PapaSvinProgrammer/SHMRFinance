package com.example.settings.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.settings.presentation.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface SettingsModule {
    @Binds
    @SettingsScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @SettingsScope
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindsViewModel(viewModel: SettingsViewModel): ViewModel
}