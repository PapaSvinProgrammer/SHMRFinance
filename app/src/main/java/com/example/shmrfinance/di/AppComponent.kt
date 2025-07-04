package com.example.shmrfinance.di

import com.example.shmrfinance.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}