package com.example.articles.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [ArticlesModule::class],
    dependencies = [AppComponent::class]
)
@ArticlesScope
interface ArticlesComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): ArticlesComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}