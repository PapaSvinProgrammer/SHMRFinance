package com.example.income.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [IncomeModule::class]
)
@IncomeScope
interface IncomeComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): IncomeComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}