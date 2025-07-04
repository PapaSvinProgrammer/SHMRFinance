package com.example.bankaccountscreen.di

import android.content.Context
import com.example.bankaccountscreen.BankAccountViewModel
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        BankAccountModule::class
    ]
)
@BankAccountScope
interface BankAccountComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): BankAccountComponent
    }

    val viewModel: BankAccountViewModel
}