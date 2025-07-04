package com.example.articles.di

import android.content.Context
import com.example.articles.ArticlesViewModel
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [ArticlesModule::class]
)
@ArticlesScope
interface ArticlesComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ArticlesComponent
    }

    val viewModel: ArticlesViewModel
}