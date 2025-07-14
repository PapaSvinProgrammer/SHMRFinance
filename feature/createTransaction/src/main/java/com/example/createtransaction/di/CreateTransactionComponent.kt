package com.example.createtransaction.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [CreateTransactionModule::class],
    dependencies = [AppComponent::class]
)
@CreateTransactionScope
interface CreateTransactionComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): CreateTransactionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}