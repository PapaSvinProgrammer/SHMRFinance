package com.example.updatetransaction.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [UpdateTransactionModule::class]
)
@UpdateTransactionScope
interface UpdateTransactionComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): UpdateTransactionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}