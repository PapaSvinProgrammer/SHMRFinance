package com.example.shmrfinance

import android.app.Application
import android.content.Context
import com.example.shmrfinance.di.AppComponent
import com.example.shmrfinance.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }