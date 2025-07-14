package com.example.room.external

import com.example.model.Category

interface CategoryServiceRoom {
    suspend fun insertAll(list: List<Category>): Result<Unit>
    suspend fun delete(id: Int): Result<Unit>
    suspend fun getAll(): Result<List<Category>>
}