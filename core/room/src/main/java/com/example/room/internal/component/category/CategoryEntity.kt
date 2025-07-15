package com.example.room.internal.component.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "category",
    indices = [
        Index(
            value = ["category_id"],
            unique = true
        )
    ]
)
internal data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "emoji")
    val emoji: String,
    @ColumnInfo(name = "is_income")
    val isIncome: Boolean
)
