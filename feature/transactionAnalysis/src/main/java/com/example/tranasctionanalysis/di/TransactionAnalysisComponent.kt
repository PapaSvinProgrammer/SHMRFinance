package com.example.tranasctionanalysis.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [TransactionAnalysisModule::class],
    dependencies = [AppComponent::class]
)
@TransactionAnalysisScope
interface TransactionAnalysisComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): TransactionAnalysisComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}