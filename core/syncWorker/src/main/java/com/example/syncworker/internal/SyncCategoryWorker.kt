package com.example.syncworker.internal

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.network.external.CategoryService
import com.example.room.external.CategoryServiceRoom
import com.example.room.external.WorkLogService
import com.example.syncworker.internal.SyncCategoryWorker.Companion.NAME
import com.example.syncworker.internal.di.ChildWorkerFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SyncCategoryWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val categoryService: CategoryService,
    private val categoryServiceRoom: CategoryServiceRoom,
    private val workLogService: WorkLogService
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            syncTimeExecution(NAME, workLogService)
            val categoryResult = categoryService.getAll()

            categoryResult.onSuccess {
                categoryServiceRoom.insertAll(it).onSuccess {
                    return@withContext Result.success()
                }.onFailure {
                    return@withContext Result.retry()
                }
            }

            return@withContext Result.failure()
        }
    }

    @AssistedFactory
    interface Factory : ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): SyncCategoryWorker
    }

    companion object {
        const val NAME = "sync_categories_data"
    }
}

internal fun WorkManager.scheduleSyncCategoryWork() {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val request = OneTimeWorkRequestBuilder<SyncCategoryWorker>()
        .setConstraints(constraints)
        .build()

    enqueueUniqueWork(
        uniqueWorkName = NAME,
        existingWorkPolicy = ExistingWorkPolicy.REPLACE,
        request = request
    )
}
