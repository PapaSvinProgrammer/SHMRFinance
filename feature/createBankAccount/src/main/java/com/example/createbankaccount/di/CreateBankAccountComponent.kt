package com.example.createbankaccount.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [CreateBankAccountModule::class],
    dependencies = [AppComponent::class]
)
@CreateBankAccountScope
interface CreateBankAccountComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): CreateBankAccountComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}