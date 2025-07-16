package com.example.shmrfinance

import android.app.Application
import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.corecomponent.AppComponent
import com.example.corecomponent.DaggerAppComponent
import com.example.shmrfinance.syncworker.SyncBankAccountWorker
import com.example.shmrfinance.syncworker.SyncCategoryWorker
import com.example.shmrfinance.syncworker.SyncTransactionWorker

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .factory()
            .create(this)

        initWorker(this)
    }
}

fun initWorker(context: Context) {
    WorkManager.getInstance(context).apply {
        enqueueUniqueWork(
            uniqueWorkName = SyncCategoryWorker.NAME,
            existingWorkPolicy = ExistingWorkPolicy.KEEP,
            request = SyncCategoryWorker.request
        )

        enqueueUniquePeriodicWork(
            uniqueWorkName = SyncBankAccountWorker.NAME,
            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
            request = SyncBankAccountWorker.request
        )

        enqueueUniquePeriodicWork(
            uniqueWorkName = SyncTransactionWorker.NAME,
            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
            request = SyncTransactionWorker.request
        )
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }