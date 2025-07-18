package com.example.syncworker.internal.di

import android.content.Context
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.example.syncworker.internal.SyncBankAccountWorker
import com.example.syncworker.internal.SyncCategoryWorker
import com.example.syncworker.internal.SyncTransactionWorker
import com.example.utils.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface WorkManagerModuleImpl {
    @Binds
    fun bindsWorkFactory(factory: DaggerWorkerFactory): WorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(SyncBankAccountWorker::class)
    fun bindSyncBankAccountFactory(factory: SyncBankAccountWorker.Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(SyncCategoryWorker::class)
    fun bindsSyncCategoryWorkerFactory(factory: SyncCategoryWorker.Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(SyncTransactionWorker::class)
    fun bindsSyncTransactionWorkerFactory(factory: SyncTransactionWorker.Factory): ChildWorkerFactory

    companion object Companion {
        @Provides
        @ApplicationScope
        fun providesWorkManager(context: Context, factory: WorkerFactory): WorkManager {
            val workManagerConfig = Configuration.Builder()
                .setWorkerFactory(factory)
                .build()

            WorkManager.initialize(context, workManagerConfig)

            return WorkManager.getInstance(context)
        }
    }
}