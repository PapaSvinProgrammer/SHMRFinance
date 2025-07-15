package com.example.room.internal.component.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(vararg entity: CategoryEntity)

    @Query("DELETE FROM category WHERE category_id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM category")
    suspend fun getAll(): List<CategoryEntity>
}