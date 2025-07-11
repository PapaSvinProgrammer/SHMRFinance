package com.example.createtransaction.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [CreateTransactionModule::class]
)
@CreateTransactionScope
interface CreateTransactionComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CreateTransactionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}