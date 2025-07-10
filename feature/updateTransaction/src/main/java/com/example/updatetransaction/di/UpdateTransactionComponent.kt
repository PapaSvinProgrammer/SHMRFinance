package com.example.updatetransaction.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [UpdateTransactionModule::class]
)
@UpdateTransactionScope
interface UpdateTransactionComponent {
    @Component.Factory
    interface Factory {
        fun create(): UpdateTransactionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}