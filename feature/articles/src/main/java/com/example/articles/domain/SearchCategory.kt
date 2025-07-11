package com.example.articles.domain

import com.example.model.Category
import javax.inject.Inject

class SearchCategory @Inject constructor() {
    fun execute(list: List<Category>, query: String): List<Category> {
        return list.filter {
            it.name.lowercase().contains(query.lowercase())
        }
    }
}