package com.example.data.internal

import com.example.data.external.CategoryRepository
import com.example.model.Category
import com.example.network.external.CategoryService
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val service: CategoryService
): CategoryRepository {
    override suspend fun getAll(): Result<List<Category>> {
        return service.getAll()
    }

    override suspend fun getAllByType(isIncome: Boolean): Result<List<Category>> {
        return service.getAllByType(isIncome)
    }
}