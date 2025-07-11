package com.example.updatebankaccount.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [UpdateBankAccountModule::class]
)
@UpdateBankAccountScope
interface UpdateBankAccountComponent {
    @Component.Factory
    interface Factory {
        fun create(): UpdateBankAccountComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}