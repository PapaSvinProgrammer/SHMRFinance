package com.example.bankaccountlist.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [BankAccountListModule::class]
)
@BankAccountListScope
interface BankAccountListComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): BankAccountListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}