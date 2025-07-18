package com.example.room.external

import com.example.room.external.model.WorkLog
import kotlinx.coroutines.flow.Flow

interface WorkLogService {
    suspend fun insert(workLog: WorkLog): Result<Unit>
    fun getAll(): Flow<List<WorkLog>>
}