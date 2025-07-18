package com.example.syncworker.external

import androidx.work.WorkManager
import androidx.work.WorkerFactory

interface WorkManagerDependency {
    val workManager: WorkManager
    val workerFactory: WorkerFactory
}