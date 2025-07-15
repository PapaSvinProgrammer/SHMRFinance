package com.example.updatebankaccount.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [UpdateBankAccountModule::class],
    dependencies = [AppComponent::class]
)
@UpdateBankAccountScope
interface UpdateBankAccountComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): UpdateBankAccountComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}