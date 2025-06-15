package com.example.network.serivce

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.model.Category

interface CategoryService {
    suspend fun getAll(): Operation<List<Category>, NetworkError>
    suspend fun getAllByType(isIncome: Boolean): Operation<List<Category>, NetworkError>
}