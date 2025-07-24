package com.example.corecomponent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.localfactory.viewModel.ViewModelFactory
import com.example.localfactory.viewModel.ViewModelKey
import com.example.utils.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface AppModule {
    @Binds
    @ApplicationScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ApplicationScope
    @IntoMap
    @ViewModelKey(AppViewModel::class)
    fun bindsAppViewModel(viewModel: AppViewModel): ViewModel
}