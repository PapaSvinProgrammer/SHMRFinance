package com.example.data.external

import com.example.model.Category

interface CategoryRepository {
    suspend fun getAll(): Result<List<Category>>
    suspend fun getAllByType(isIncome: Boolean): Result<List<Category>>
}