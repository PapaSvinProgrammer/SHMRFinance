package com.example.data.external.local

import com.example.model.Category

interface CategoryRepositoryRoom {
    suspend fun insertAll(list: List<Category>)
    suspend fun delete(id: Int)
    suspend fun getAll(): List<Category>
}