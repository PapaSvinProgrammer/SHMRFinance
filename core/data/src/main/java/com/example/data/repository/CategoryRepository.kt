package com.example.data.repository

import com.example.common.NetworkError
import com.example.common.Result
import com.example.model.Category

interface CategoryRepository {
    suspend fun getAll(): Result<List<Category>, NetworkError>
    suspend fun getAllByType(isIncome: Boolean): Result<List<Category>, NetworkError>
}