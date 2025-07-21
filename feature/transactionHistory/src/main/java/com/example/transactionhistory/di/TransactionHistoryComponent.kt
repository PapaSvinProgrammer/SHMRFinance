package com.example.transactionhistory.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [TransactionHistoryModule::class],
    dependencies = [AppComponent::class]
)
@TransactionHistoryScope
interface TransactionHistoryComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): TransactionHistoryComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}