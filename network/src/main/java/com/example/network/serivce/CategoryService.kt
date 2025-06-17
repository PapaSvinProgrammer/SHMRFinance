package com.example.network.serivce

import com.example.network.core.NetworkRootError
import com.example.network.core.Operation
import com.example.network.model.Category

interface CategoryService {
    suspend fun getAll(): Operation<List<Category>, NetworkRootError>
    suspend fun getAllByType(isIncome: Boolean): Operation<List<Category>, NetworkRootError>
}