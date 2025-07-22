package com.example.shmrfinance

import android.app.Application
import android.content.Context
import com.example.corecomponent.AppComponent
import com.example.corecomponent.DaggerAppComponent
import com.example.syncworker.external.initWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .factory()
            .create(this)

        appComponent.preferencesRepository
            .getFrequencyValue()
            .onEach { initWorker(appComponent.workManager, it) }
            .launchIn(CoroutineScope(Dispatchers.IO))
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }