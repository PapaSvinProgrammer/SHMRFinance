package com.example.expenses.di

import android.content.Context
import com.example.expenses.ExpensesViewModel
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

    val viewModel: ExpensesViewModel
}