package com.example.network.external

import com.example.model.Category

interface CategoryService {
    suspend fun getAll(): Result<List<Category>>
    suspend fun getAllByType(isIncome: Boolean): Result<List<Category>>
}