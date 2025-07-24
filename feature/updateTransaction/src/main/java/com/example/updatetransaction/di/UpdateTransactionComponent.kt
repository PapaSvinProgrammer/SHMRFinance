package com.example.updatetransaction.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [UpdateTransactionModule::class],
    dependencies = [AppComponent::class]
)
@UpdateTransactionScope
interface UpdateTransactionComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): UpdateTransactionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}