package com.example.shmrfinance.syncworker

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.network.internal.common.multiRequest
import com.example.shmrfinance.appComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class SyncTransactionWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    val transactionRepositoryRoom = context.appComponent.transactionRepositoryRoom
    val transactionRepository = context.appComponent.transactionRepository

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            syncCreateTransaction()
            syncUpdateTransaction()
            syncDeleteTransaction()

            transactionRepositoryRoom.deleteAll()

            return@withContext Result.success()
        }
    }

    suspend fun syncCreateTransaction() {
        val createdTransactions = transactionRepositoryRoom.getCreated()

        createdTransactions.onSuccess { list ->
            multiRequest(list) {
                transactionRepository.create(it)
            }
        }
    }

    suspend fun syncUpdateTransaction() {
        val updatedTransactions = transactionRepositoryRoom.getUpdated()

        updatedTransactions.onSuccess { list ->
            multiRequest(list) { transaction ->
                transactionRepository.update(
                    id = transaction.account.id,
                    request = transaction.toRequest()
                )
            }
        }
    }

    suspend fun syncDeleteTransaction() {
        val deletedTransactions = transactionRepositoryRoom.getDeleted()

        deletedTransactions.onSuccess { list ->
            multiRequest(list) { id ->
                transactionRepository.delete(id)
            }
        }
    }

    companion object {
        const val NAME = "sync_transaction_worker"

        private val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        private val inputData = workDataOf(
            Constants.NAME_KEY to NAME
        )

        val request = PeriodicWorkRequest
            .Builder(
                workerClass = SyncTransactionWorker::class.java,
                repeatInterval = 4,
                repeatIntervalTimeUnit = TimeUnit.HOURS
            )
            .setInputData(inputData)
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