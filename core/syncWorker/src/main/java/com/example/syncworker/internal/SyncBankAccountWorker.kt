package com.example.syncworker.internal

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkerParameters
import com.example.network.external.BankAccountService
import com.example.room.external.BankAccountServiceRoom
import com.example.room.external.WorkLogService
import com.example.syncworker.internal.di.ChildWorkerFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

internal class SyncBankAccountWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val bankAccountService: BankAccountService,
    private val bankAccountServiceRoom: BankAccountServiceRoom,
    private val workLogService: WorkLogService
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            syncTimeExecution(NAME, workLogService)
            val bankAccounts = bankAccountService.getAll()

            bankAccounts.onSuccess {
                bankAccountServiceRoom.insertAll(it).onSuccess {
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
        override fun create(context: Context, params: WorkerParameters): SyncBankAccountWorker
    }

    companion object {
        const val NAME = "sync_bank_account_data"

        private val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = PeriodicWorkRequest
            .Builder(
                workerClass = SyncBankAccountWorker::class.java,
                repeatInterval = 12,
                repeatIntervalTimeUnit = TimeUnit.HOURS
            )
            .setConstraints(constraints)
            .build()
    }
}