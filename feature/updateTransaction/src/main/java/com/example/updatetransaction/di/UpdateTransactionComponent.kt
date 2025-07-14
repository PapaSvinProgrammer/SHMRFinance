package com.example.updatetransaction.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [UpdateTransactionModule::class],
    dependencies = [AppComponent::class]
)
@UpdateTransactionScope
interface UpdateTransactionComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): UpdateTransactionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}