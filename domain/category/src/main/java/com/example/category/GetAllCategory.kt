package com.example.category

import com.example.common.request
import com.example.data.repository.CategoryRepository
import com.example.model.Category
import javax.inject.Inject

class GetAllCategory @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend fun execute(): Result<List<Category>> {
        return request { categoryRepository.getAll() }
    }
}