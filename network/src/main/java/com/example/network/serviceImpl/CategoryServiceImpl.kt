package com.example.network.serviceImpl

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.safeCall
import com.example.network.model.Category
import com.example.network.serivce.CategoryService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import jakarta.inject.Inject

class CategoryServiceImpl @Inject constructor(
    private val client: HttpClient
): CategoryService {
    override suspend fun getAll(): Operation<List<Category>, NetworkError> {
        return safeCall {
            client.get("v1/categories")
        }
    }

    override suspend fun getAllByType(isIncome: Boolean): Operation<List<Category>, NetworkError> {
        return safeCall {
            client.get("v1/categories/type/$isIncome")
        }
    }
}