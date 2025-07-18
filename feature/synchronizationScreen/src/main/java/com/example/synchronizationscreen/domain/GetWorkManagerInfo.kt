package com.example.synchronizationscreen.domain

import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkQuery
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class GetWorkManagerInfo @Inject constructor(
    private val workManager: WorkManager
): UseCase<Unit, List<WorkInfo>>(Dispatchers.IO) {
    override suspend fun run(params: Unit): List<WorkInfo> {
        val workQuery = WorkQuery.Builder
            .fromUniqueWorkNames(
                listOf(
                    "sync_bank_account_data",
                    "sync_categories_data",
                    "sync_transaction_worker"
                )
            ).build()

        return workManager.getWorkInfos(workQuery).get()
    }
}