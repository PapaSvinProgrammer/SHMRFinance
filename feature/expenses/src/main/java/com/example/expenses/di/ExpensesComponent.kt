package com.example.expenses.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [ExpensesModule::class],
    dependencies = [AppComponent::class]
)
@ExpensesScope
interface ExpensesComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ExpensesComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}