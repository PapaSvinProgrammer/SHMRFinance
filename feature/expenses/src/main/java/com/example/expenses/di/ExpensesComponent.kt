package com.example.expenses.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [ExpensesModule::class],
    dependencies = [AppComponent::class]
)
@ExpensesScope
interface ExpensesComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): ExpensesComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}