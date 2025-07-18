package com.example.syncworker.internal

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkerParameters
import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.network.external.TransactionService
import com.example.network.internal.common.multiRequest
import com.example.room.external.TransactionServiceRoom
import com.example.room.external.WorkLogService
import com.example.syncworker.internal.di.ChildWorkerFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

internal class SyncTransactionWorker @AssistedInject constructor (
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val transactionService: TransactionService,
    private val transactionServiceRoom: TransactionServiceRoom,
    private val workLogService: WorkLogService
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            syncTimeExecution(NAME, workLogService)

            syncCreateTransaction()
            syncUpdateTransaction()
            syncDeleteTransaction()

            transactionServiceRoom.deleteAll()
            return@withContext Result.success()
        }
    }

    suspend fun syncCreateTransaction() {
        val createdTransactions = transactionServiceRoom.getCreated()

        createdTransactions.onSuccess { list ->
            multiRequest(list) {
                transactionService.create(it)
            }
        }
    }

    suspend fun syncUpdateTransaction() {
        val updatedTransactions = transactionServiceRoom.getUpdated()

        updatedTransactions.onSuccess { list ->
            multiRequest(list) { transaction ->
                transactionService.update(
                    id = transaction.account.id,
                    request = transaction.toRequest()
                )
            }
        }
    }

    suspend fun syncDeleteTransaction() {
        val deletedTransactions = transactionServiceRoom.getDeleted()

        deletedTransactions.onSuccess { list ->
            multiRequest(list) { id ->
                transactionService.delete(id)
            }
        }
    }

    @AssistedFactory
    interface Factory : ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): SyncTransactionWorker
    }

    companion object {
        const val NAME = "sync_transaction_worker"

        private val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = PeriodicWorkRequest
            .Builder(
                workerClass = SyncTransactionWorker::class.java,
                repeatInterval = 4,
                repeatIntervalTimeUnit = TimeUnit.HOURS
            )
            .setConstraints(constraints)
            .build()
    }
}

private fun Transaction.toRequest(): TransactionRequest {
    return TransactionRequest(
        accountId = account.id,
        categoryId = category.id,
        amount = amount.toString(),
        transactionDate = transactionDate,
        comment = comment
    )
}
