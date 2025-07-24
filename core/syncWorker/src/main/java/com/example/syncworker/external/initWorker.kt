package com.example.syncworker.external

import androidx.work.WorkManager
import com.example.syncworker.internal.scheduleSyncBankAccountWork
import com.example.syncworker.internal.scheduleSyncCategoryWork
import com.example.syncworker.internal.scheduleSyncTransactionWork

fun initWorker(workManager: WorkManager, interval: Int) {
    workManager.scheduleSyncCategoryWork()
    workManager.scheduleSyncBankAccountWork(interval)
    workManager.scheduleSyncTransactionWork(interval)
}