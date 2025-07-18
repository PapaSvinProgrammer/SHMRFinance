package com.example.syncworker.internal

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.data.external.local.CategoryRepositoryRoom
import com.example.data.external.remote.CategoryRepository
import com.example.syncworker.internal.di.ChildWorkerFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SyncCategoryWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val categoryRepository: CategoryRepository,
    private val categoryRepositoryRoom: CategoryRepositoryRoom
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val categoryResult = categoryRepository.getAll()

            categoryResult.onSuccess {
                categoryRepositoryRoom.insertAll(it).onSuccess {
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

        private val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = OneTimeWorkRequestBuilder<SyncCategoryWorker>()
            .setConstraints(constraints)
            .build()
    }
}
