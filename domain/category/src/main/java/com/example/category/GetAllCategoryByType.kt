package com.example.category

import com.example.common.request
import com.example.data.external.CategoryRepository
import com.example.model.Category
import javax.inject.Inject

class GetAllCategoryByType @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend fun execute(isIncome: Boolean): Result<List<Category>> {
        return request { categoryRepository.getAllByType(isIncome) }
    }
}