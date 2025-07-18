package com.example.room.internal.service

import com.example.model.Category
import com.example.room.external.CategoryServiceRoom
import com.example.room.internal.component.category.CategoryDao
import com.example.room.internal.mapper.toDomain
import com.example.room.internal.mapper.toEntity
import com.example.room.internal.safeCall
import javax.inject.Inject

internal class CategoryServiceRoomImpl @Inject constructor(
    private val dao: CategoryDao
): CategoryServiceRoom {
    override suspend fun insertAll(list: List<Category>): Result<Unit> {
        return safeCall {
            val data = list.toEntity().toTypedArray()
            dao.insert(*data)
        }
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return safeCall { dao.delete(id) }
    }

    override suspend fun getAll(): Result<List<Category>> {
        return safeCall {
            dao.getAll()
        }.map { it.toDomain() }
    }
}