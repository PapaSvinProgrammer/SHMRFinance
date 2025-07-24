package com.example.articles.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [ArticlesModule::class],
    dependencies = [AppComponent::class]
)
@ArticlesScope
interface ArticlesComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ArticlesComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}