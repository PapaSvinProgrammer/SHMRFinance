package com.example.tranasctionanalysis.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [TransactionAnalysisModule::class],
    dependencies = [AppComponent::class]
)
@TransactionAnalysisScope
interface TransactionAnalysisComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): TransactionAnalysisComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}