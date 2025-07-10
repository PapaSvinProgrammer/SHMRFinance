package com.example.expenses.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [ExpensesModule::class]
)
@ExpensesScope
interface ExpensesComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ExpensesComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}