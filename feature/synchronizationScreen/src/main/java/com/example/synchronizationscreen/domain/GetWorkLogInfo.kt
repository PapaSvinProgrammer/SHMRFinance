package com.example.synchronizationscreen.domain

import com.example.room.external.WorkLogService
import com.example.room.external.model.WorkLog
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetWorkLogInfo @Inject constructor(
    private val workLogService: WorkLogService
) {
    fun execute(): Flow<List<WorkLog>> {
        return workLogService.getAll()
    }
}