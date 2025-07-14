package com.example.data.internal.local

import android.util.Log
import com.example.data.external.local.CategoryRepositoryRoom
import com.example.model.Category
import com.example.room.external.CategoryServiceRoom
import javax.inject.Inject

internal class CategoryRepositoryRoomImpl @Inject constructor(
    private val service: CategoryServiceRoom
): CategoryRepositoryRoom {
    override suspend fun insertAll(list: List<Category>): Result<Unit> {
        return service.insertAll(list)
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return service.delete(id)
    }

    override suspend fun getAll(): Result<List<Category>> {
        Log.d("RRRR", service.toString())
        return service.getAll()
    }
}