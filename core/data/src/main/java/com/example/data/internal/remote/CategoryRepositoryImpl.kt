package com.example.data.internal.remote

import com.example.data.external.remote.CategoryRepository
import com.example.model.Category
import com.example.network.connectivityState.NetworkConnection
import com.example.network.external.CategoryService
import com.example.room.external.CategoryServiceRoom
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val service: CategoryService,
    private val serviceRoom: CategoryServiceRoom,
    private val networkConnection: NetworkConnection
): CategoryRepository {
    override suspend fun getAll(): Result<List<Category>> {
        if (networkConnection.isOnline()) {
            return service.getAll()
        }

        return serviceRoom.getAll()
    }

    override suspend fun getAllByType(isIncome: Boolean): Result<List<Category>> {
        return service.getAllByType(isIncome)
    }
}