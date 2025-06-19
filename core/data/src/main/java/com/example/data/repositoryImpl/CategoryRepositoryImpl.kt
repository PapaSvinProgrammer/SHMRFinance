package com.example.data.repositoryImpl

import com.example.common.NetworkError
import com.example.common.Result
import com.example.data.repository.CategoryRepository
import com.example.model.Category
import com.example.network.serivce.CategoryService
import jakarta.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val service: CategoryService
): CategoryRepository {
    override suspend fun getAll(): Result<List<Category>, NetworkError> {
        return service.getAll()
    }

    override suspend fun getAllByType(isIncome: Boolean): Result<List<Category>, NetworkError> {
        return service.getAllByType(isIncome)
    }
}