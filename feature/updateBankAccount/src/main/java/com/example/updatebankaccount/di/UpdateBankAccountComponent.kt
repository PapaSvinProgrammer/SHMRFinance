package com.example.updatebankaccount.di

import com.example.updatebankaccount.UpdateBankAccountViewModel
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

    val viewModel: UpdateBankAccountViewModel
}