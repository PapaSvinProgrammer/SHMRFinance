package com.example.createbankaccount.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [CreateBankAccountModule::class],
    dependencies = [AppComponent::class]
)
@CreateBankAccountScope
interface CreateBankAccountComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): CreateBankAccountComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}