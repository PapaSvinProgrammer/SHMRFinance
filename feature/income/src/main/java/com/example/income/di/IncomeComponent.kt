package com.example.income.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [IncomeModule::class],
    dependencies = [AppComponent::class]
)
@IncomeScope
interface IncomeComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): IncomeComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}