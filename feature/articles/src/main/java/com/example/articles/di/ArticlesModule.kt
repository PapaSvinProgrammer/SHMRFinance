package com.example.articles.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.articles.presentation.ArticlesViewModel
import com.example.category.GetAllCategory
import com.example.data.external.CategoryRepository
import com.example.localviewmodelfactory.ViewModelFactory
import com.example.localviewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface ArticlesModule {
    @Binds
    @ArticlesScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ArticlesScope
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    fun bindsArticlesViewModel(viewMode: ArticlesViewModel): ViewModel

    companion object {
        @Provides
        @ArticlesScope
        fun providesGetAllCategories(repository: CategoryRepository): GetAllCategory {
            return GetAllCategory(repository)
        }
    }
}