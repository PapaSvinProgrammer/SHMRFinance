package com.example.transactionhistory.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [TransactionModule::class],
    dependencies = [AppComponent::class]
)
@TransactionHistoryScope
interface TransactionComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): TransactionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}