package com.example.income.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [IncomeModule::class],
    dependencies = [AppComponent::class]
)
@IncomeScope
interface IncomeComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): IncomeComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}