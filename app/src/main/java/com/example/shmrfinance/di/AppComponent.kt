package com.example.shmrfinance.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.shmrfinance.di.viewModel.ViewModelModule
import com.example.shmrfinance.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun viewModelFactory(): ViewModelProvider.Factory
}