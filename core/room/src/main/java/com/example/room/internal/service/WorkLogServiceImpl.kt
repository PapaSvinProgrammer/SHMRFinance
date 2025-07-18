package com.example.room.internal.service

import com.example.room.external.WorkLogService
import com.example.room.external.model.WorkLog
import com.example.room.internal.component.workLog.WorkLogDao
import com.example.room.internal.mapper.toDomain
import com.example.room.internal.mapper.toEntity
import com.example.room.internal.safeCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class WorkLogServiceImpl @Inject constructor(
    private val dao: WorkLogDao
) : WorkLogService {
    override suspend fun insert(workLog: WorkLog): Result<Unit> {
        return safeCall {
            dao.insert(workLog.toEntity())
        }
    }

    override fun getAll(): Flow<List<WorkLog>> {
        return dao.getAll().map { flow ->
            flow.map { item ->
                item.toDomain()
            }
        }
    }
}