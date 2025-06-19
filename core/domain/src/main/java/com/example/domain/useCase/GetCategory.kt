package com.example.domain.useCase

import com.example.common.NetworkError
import com.example.common.Result
import com.example.common.request
import com.example.model.Category
import jakarta.inject.Inject

class GetCategory @Inject constructor(
    private val categoryRepository: com.example.data.repository.CategoryRepository
) {
    suspend fun getAll(): Result<List<Category>, NetworkError> {
       return request { categoryRepository.getAll() }
    }

    suspend fun getAllByType(isIncome: Boolean): Result<List<Category>, NetworkError> {
        return request { categoryRepository.getAllByType(isIncome) }
    }
}