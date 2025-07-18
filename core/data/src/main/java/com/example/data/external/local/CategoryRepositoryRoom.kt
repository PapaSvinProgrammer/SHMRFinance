package com.example.data.external.local

import com.example.model.Category

interface CategoryRepositoryRoom {
    suspend fun insertAll(list: List<Category>): Result<Unit>
    suspend fun delete(id: Int): Result<Unit>
    suspend fun getAll(): Result<List<Category>>
}