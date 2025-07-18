package com.example.room.internal.mapper

import com.example.room.external.model.WorkLog
import com.example.room.internal.component.workLog.WorkLogEntity

internal fun WorkLog.toEntity(): WorkLogEntity {
    return WorkLogEntity(
        id = id,
        workerName = workerName,
        startTime = startTime
    )
}

internal fun WorkLogEntity.toDomain(): WorkLog {
    return WorkLog(
        id = id,
        workerName = workerName,
        startTime = startTime
    )
}

internal fun List<WorkLogEntity>.toDomain(): List<WorkLog> {
    return map { it.toDomain() }
}