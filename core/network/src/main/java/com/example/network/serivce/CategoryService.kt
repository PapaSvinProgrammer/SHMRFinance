package com.example.network.serivce

import com.example.model.Category

interface CategoryService {
    suspend fun getAll(): Result<List<Category>>
    suspend fun getAllByType(isIncome: Boolean): Result<List<Category>>
}