package com.example.shmrfinance.data.repository

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.model.Category
import com.example.network.serivce.CategoryService
import com.example.shmrfinance.domain.repository.CategoryRepository
import jakarta.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val service: CategoryService
): CategoryRepository {
    override suspend fun getAll(): Operation<List<Category>, NetworkError> {
        return service.getAll()
    }

    override suspend fun getAllByType(isIncome: Boolean): Operation<List<Category>, NetworkError> {
        return service.getAllByType(isIncome)
    }
}