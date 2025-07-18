package com.example.room.internal.component.workLog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WorkLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: WorkLogEntity)

    @Query("SELECT * FROM work_log")
    fun getAll(): Flow<List<WorkLogEntity>>
}
