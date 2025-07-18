package com.example.syncworker.internal

import com.example.room.external.WorkLogService
import com.example.room.external.model.WorkLog

internal suspend fun syncTimeExecution(
    name: String,
    service: WorkLogService
) {
    val workLog = WorkLog(
        workerName = name,
        startTime = System.currentTimeMillis()
    )

    service.insert(workLog)
}