package com.example.articles.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ArticlesModule::class
    ]
)
internal interface ArticlesComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ArticlesComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}