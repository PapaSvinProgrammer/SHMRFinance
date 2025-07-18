package com.example.room.internal.component.workLog

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "work_log",
    indices = [
        Index(
            value = ["worker_name"],
            unique = true
        )
    ]
)
internal data class WorkLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "worker_name") val workerName: String,
    @ColumnInfo(name = "start_time") val startTime: Long
)
