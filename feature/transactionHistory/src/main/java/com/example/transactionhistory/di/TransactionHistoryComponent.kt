package com.example.transactionhistory.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [TransactionHistoryModule::class],
    dependencies = [AppComponent::class]
)
@TransactionHistoryScope
interface TransactionHistoryComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): TransactionHistoryComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}