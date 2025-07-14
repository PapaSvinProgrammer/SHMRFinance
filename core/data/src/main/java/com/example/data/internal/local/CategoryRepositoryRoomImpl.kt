package com.example.data.internal.local

import com.example.data.external.local.CategoryRepositoryRoom
import com.example.model.Category
import com.example.room.internal.component.category.CategoryDao
import com.example.room.internal.mapper.toDomain
import com.example.room.internal.mapper.toEntity
import javax.inject.Inject

internal class CategoryRepositoryRoomImpl @Inject constructor(
    private val dao: CategoryDao
): CategoryRepositoryRoom {
    override suspend fun insertAll(list: List<Category>) {
        val args = list.toEntity().toTypedArray()
        dao.insert(*args)
    }

    override suspend fun delete(id: Int) {
        dao.delete(id)
    }

    override suspend fun getAll(): List<Category> {
        return dao.getAll().toDomain()
    }
}