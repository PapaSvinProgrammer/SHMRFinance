package com.example.transactionhistory.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [TransactionModule::class]
)
@TransactionScope
interface TransactionComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TransactionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}