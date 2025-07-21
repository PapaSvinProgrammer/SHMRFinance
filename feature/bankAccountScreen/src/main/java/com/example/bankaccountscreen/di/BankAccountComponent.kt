package com.example.bankaccountscreen.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [
        BankAccountModule::class
    ],
    dependencies = [AppComponent::class]
)
@BankAccountScope
interface BankAccountComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): BankAccountComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}