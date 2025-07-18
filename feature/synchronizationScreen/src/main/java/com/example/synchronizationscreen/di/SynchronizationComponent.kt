package com.example.synchronizationscreen.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [SynchronizationModule::class],
    dependencies = [AppComponent::class]
)
@SynchronizationScope
interface SynchronizationComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): SynchronizationComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}