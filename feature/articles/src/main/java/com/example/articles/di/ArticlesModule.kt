package com.example.articles.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.articles.ArticlesViewModel
import com.example.data.internal.di.DataModule
import com.example.localviewmodelfactory.ViewModelFactory
import com.example.localviewmodelfactory.ViewModelKey
import com.example.network.external.di.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        DataModule::class
    ]
)
interface ArticlesModule {
    @Binds
    @ArticlesScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ArticlesScope
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    fun bindsArticlesViewModel(viewMode: ArticlesViewModel): ViewModel
}