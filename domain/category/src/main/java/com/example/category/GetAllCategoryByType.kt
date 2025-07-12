package com.example.category

import com.example.data.external.CategoryRepository
import com.example.model.Category
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetAllCategoryByType @Inject constructor(
    private val categoryRepository: CategoryRepository
) : UseCase<Boolean, Result<List<Category>>>(Dispatchers.IO) {
    override suspend fun run(params: Boolean): Result<List<Category>> {
        return categoryRepository.getAllByType(params)
    }
}
