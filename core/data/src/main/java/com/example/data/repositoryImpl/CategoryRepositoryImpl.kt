package com.example.data.repositoryImpl

import com.example.data.repository.CategoryRepository
import com.example.model.Category
import com.example.network.serivce.CategoryService
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val service: CategoryService
): CategoryRepository {
    override suspend fun getAll(): Result<List<Category>> {
        return service.getAll()
    }

    override suspend fun getAllByType(isIncome: Boolean): Result<List<Category>> {
        return service.getAllByType(isIncome)
    }
}