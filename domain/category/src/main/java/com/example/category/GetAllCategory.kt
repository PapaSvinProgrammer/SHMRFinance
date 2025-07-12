package com.example.category

import com.example.data.external.CategoryRepository
import com.example.model.Category
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetAllCategory @Inject constructor(
    private val categoryRepository: CategoryRepository
) : UseCase<Unit, Result<List<Category>>>(Dispatchers.IO) {
    override suspend fun run(params: Unit): Result<List<Category>> {
        return categoryRepository.getAll()
    }
}
