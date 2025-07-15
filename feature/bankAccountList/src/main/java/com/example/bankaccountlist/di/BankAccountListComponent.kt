package com.example.bankaccountlist.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [BankAccountListModule::class],
    dependencies = [AppComponent::class]
)
@BankAccountListScope
interface BankAccountListComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): BankAccountListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}