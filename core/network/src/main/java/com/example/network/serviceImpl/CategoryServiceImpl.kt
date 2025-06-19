package com.example.network.serviceImpl

import com.example.common.NetworkError
import com.example.common.Result
import com.example.common.map
import com.example.model.Category
import com.example.network.common.safeCall
import com.example.network.mapper.toDomain
import com.example.network.model.CategoryDto
import com.example.network.serivce.CategoryService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import jakarta.inject.Inject

class CategoryServiceImpl @Inject constructor(
    private val client: HttpClient
): CategoryService {
    override suspend fun getAll(): Result<List<Category>, NetworkError> {
        return safeCall<List<CategoryDto>> {
            client.get("v1/categories")
        }.map { response ->
            response.map { it.toDomain() }
        }
    }

    override suspend fun getAllByType(isIncome: Boolean): Result<List<Category>, NetworkError> {
        return safeCall<List<CategoryDto>> {
            client.get("v1/categories/type/$isIncome")
        }.map { response ->
            response.map { it.toDomain() }
        }
    }
}