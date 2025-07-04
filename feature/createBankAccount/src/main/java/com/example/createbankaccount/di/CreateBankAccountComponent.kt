package com.example.createbankaccount.di

import android.content.Context
import com.example.createbankaccount.CreateBankAccountViewModel
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [CreateBankAccountModule::class]
)
@CreateBankAccountScope
interface CreateBankAccountComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CreateBankAccountComponent
    }

    val viewModel: CreateBankAccountViewModel
}