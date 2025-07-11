package com.example.createtransaction.domain

import com.example.model.Category
import javax.inject.Inject

class FilterCategories @Inject constructor() {
    fun execute(list: List<Category>, isIncome: Boolean): List<Category> {
        return list.filter { it.isIncome == isIncome }
    }
}
