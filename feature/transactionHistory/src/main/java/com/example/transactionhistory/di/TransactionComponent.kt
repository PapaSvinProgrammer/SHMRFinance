package com.example.transactionhistory.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [TransactionModule::class],
    dependencies = [AppComponent::class]
)
@TransactionScope
interface TransactionComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): TransactionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}