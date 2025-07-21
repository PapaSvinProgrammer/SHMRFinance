package com.example.bankaccountlist.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [BankAccountListModule::class],
    dependencies = [AppComponent::class]
)
@BankAccountListScope
interface BankAccountListComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): BankAccountListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}