package com.example.shmrfinance.domain.useCase

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.request
import com.example.network.model.Category
import com.example.shmrfinance.domain.repository.CategoryRepository
import jakarta.inject.Inject

class GetCategory @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend fun getAll(): Operation<List<Category>, NetworkError> {
       return request { categoryRepository.getAll() }
    }

    suspend fun getAllByType(isIncome: Boolean): Operation<List<Category>, NetworkError> {
        return request { categoryRepository.getAllByType(isIncome) }
    }
}