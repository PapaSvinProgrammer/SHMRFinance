package com.example.bankaccountscreen.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
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
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): BankAccountComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}