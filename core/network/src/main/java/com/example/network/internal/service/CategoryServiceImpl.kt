package com.example.network.internal.service

import com.example.model.Category
import com.example.network.internal.common.safeCall
import com.example.network.internal.mapper.toDomain
import com.example.network.internal.model.CategoryDto
import com.example.network.external.CategoryService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

internal class CategoryServiceImpl @Inject constructor(
    private val client: HttpClient
): CategoryService {
    override suspend fun getAll(): Result<List<Category>> {
        return safeCall<List<CategoryDto>> {
            client.get("v1/categories")
        }.map { response ->
            response.map { it.toDomain() }
        }
    }

    override suspend fun getAllByType(isIncome: Boolean): Result<List<Category>> {
        return safeCall<List<CategoryDto>> {
            client.get("v1/categories/type/$isIncome")
        }.map { response ->
            response.map { it.toDomain() }
        }
    }
}