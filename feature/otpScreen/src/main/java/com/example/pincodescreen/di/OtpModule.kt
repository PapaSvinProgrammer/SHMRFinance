package com.example.pincodescreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.pincodescreen.presentation.OtpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface OtpModule {
    @Binds
    @OtpScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @OtpScope
    @IntoMap
    @ViewModelKey(OtpViewModel::class)
    fun bindsOtpViewModel(viewModel: OtpViewModel): ViewModel
}