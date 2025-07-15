package com.example.shmrfinance.syncworker

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.shmrfinance.appComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SyncCategoryWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val categoryResult = context.appComponent.categoryRepository.getAll()

            categoryResult.onSuccess {
                context.appComponent.categoryRepositoryRoom.insertAll(it).onSuccess {
                    return@withContext Result.success()
                }.onFailure {
                    return@withContext Result.retry()
                }
            }

            return@withContext Result.failure()
        }
    }

    companion object {
        const val NAME = "sync_categories_data"

        private val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = OneTimeWorkRequestBuilder<SyncCategoryWorker>()
            .setConstraints(constraints)
            .build()
    }
}
