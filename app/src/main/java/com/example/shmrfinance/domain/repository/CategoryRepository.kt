package com.example.shmrfinance.domain.repository

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.model.Category

interface CategoryRepository {
    suspend fun getAll(): Operation<List<Category>, NetworkError>
    suspend fun getAllByType(isIncome: Boolean): Operation<List<Category>, NetworkError>
}