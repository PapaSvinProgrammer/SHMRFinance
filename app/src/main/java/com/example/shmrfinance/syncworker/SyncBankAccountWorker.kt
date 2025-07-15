package com.example.shmrfinance.syncworker

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkerParameters
import com.example.shmrfinance.appComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class SyncBankAccountWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    val bankAccountRepository = context.appComponent.bankAccountRepository
    val bankAccountRepositoryRoom = context.appComponent.bankAccountRepositoryRoom

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val bankAccounts = bankAccountRepository.getAll()
            bankAccounts.onSuccess {
                bankAccountRepositoryRoom.insertAll(it).onSuccess {
                    return@withContext Result.success()
                }.onFailure {
                    return@withContext Result.retry()
                }
            }

            return@withContext Result.failure()
        }
    }

    companion object {
        const val NAME = "sync_bank_account_data"

        private val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = PeriodicWorkRequest.Builder(
            workerClass = SyncBankAccountWorker::class.java,
            repeatInterval = 12,
            repeatIntervalTimeUnit = TimeUnit.HOURS
        ).setConstraints(constraints).build()
    }
}