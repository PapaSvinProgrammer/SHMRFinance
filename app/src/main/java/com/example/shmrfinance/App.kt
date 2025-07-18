package com.example.shmrfinance

import android.app.Application
import android.content.Context
import com.example.corecomponent.AppComponent
import com.example.corecomponent.DaggerAppComponent
import com.example.syncworker.external.initWorker

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .factory()
            .create(this)

        initWorker(appComponent.workManager)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }