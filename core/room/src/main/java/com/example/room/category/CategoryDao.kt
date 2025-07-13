package com.example.room.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(entity: CategoryEntity)

    @Query("DELETE FROM category WHERE category_id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM category")
    suspend fun getAll(): List<CategoryEntity>
}