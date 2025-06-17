package com.example.network.serviceImpl

import com.example.network.core.NetworkRootError
import com.example.network.core.Operation
import com.example.network.model.Category
import com.example.network.serivce.CategoryService

class CategoryServiceImpl: CategoryService {
    override suspend fun getAll(): Operation<List<Category>, NetworkRootError> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllByType(isIncome: Boolean): Operation<List<Category>, NetworkRootError> {
        TODO("Not yet implemented")
    }
}