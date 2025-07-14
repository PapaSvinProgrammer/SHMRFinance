package com.example.category

import com.example.data.external.local.CategoryRepositoryRoom
import com.example.data.external.remote.CategoryRepository
import com.example.model.Category
import com.example.network.connectivityState.NetworkConnection
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetAllCategory @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val categoryRepositoryRoom: CategoryRepositoryRoom,
    private val networkConnection: NetworkConnection
) : UseCase<Unit, Result<List<Category>>>(Dispatchers.IO) {
    override suspend fun run(params: Unit): Result<List<Category>> {
        if (networkConnection.isOnline()) {
            return categoryRepository.getAll()
        }

        return categoryRepositoryRoom.getAll()
    }
}
