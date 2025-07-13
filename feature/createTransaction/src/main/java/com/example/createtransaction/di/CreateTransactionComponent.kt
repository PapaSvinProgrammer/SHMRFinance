package com.example.createtransaction.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [CreateTransactionModule::class],
    dependencies = [AppComponent::class]
)
@CreateTransactionScope
interface CreateTransactionComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): CreateTransactionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}