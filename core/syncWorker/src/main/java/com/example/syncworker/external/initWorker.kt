package com.example.syncworker.external

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.syncworker.internal.SyncBankAccountWorker
import com.example.syncworker.internal.SyncCategoryWorker
import com.example.syncworker.internal.SyncTransactionWorker

fun initWorker(workManager: WorkManager) {
    workManager.apply {
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