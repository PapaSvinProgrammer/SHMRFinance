package com.example.category

import com.example.data.external.local.CategoryRepositoryRoom
import com.example.model.Category
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SaveCategory @Inject constructor(
    private val categoryRepositoryRoom: CategoryRepositoryRoom
): UseCase<List<Category>, Result<Unit>>(Dispatchers.IO) {
    override suspend fun run(params: List<Category>): Result<Unit> {
        return categoryRepositoryRoom.insertAll(params)
    }
}