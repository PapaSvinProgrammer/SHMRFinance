package com.example.updatebankaccount.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [UpdateBankAccountModule::class],
    dependencies = [AppComponent::class]
)
@UpdateBankAccountScope
interface UpdateBankAccountComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): UpdateBankAccountComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}