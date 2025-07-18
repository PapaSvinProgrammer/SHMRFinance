package com.example.syncworker.internal.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

internal interface ChildWorkerFactory {
    fun create(context: Context, params: WorkerParameters): ListenableWorker
}