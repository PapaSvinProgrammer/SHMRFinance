package com.example.settings.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.settings.presentation.colorSelector.ColorSelectorViewModel
import com.example.settings.presentation.haptic.HapticViewModel
import com.example.settings.presentation.settings.SettingsViewModel
import com.example.settings.presentation.settingsOtp.SettingsOtpViewModel
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
    fun bindsSettingsViewModel(viewModel: SettingsViewModel): ViewModel

    @Binds
    @SettingsScope
    @IntoMap
    @ViewModelKey(ColorSelectorViewModel::class)
    fun bindsColorSelectorViewModel(viewModel: ColorSelectorViewModel): ViewModel

    @Binds
    @SettingsScope
    @IntoMap
    @ViewModelKey(SettingsOtpViewModel::class)
    fun bindsSettingsOtpViewModel(viewModel: SettingsOtpViewModel): ViewModel

    @Binds
    @SettingsScope
    @IntoMap
    @ViewModelKey(HapticViewModel::class)
    fun bindsHapticViewModel(viewModel: HapticViewModel): ViewModel
}