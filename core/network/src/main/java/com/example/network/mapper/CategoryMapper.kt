package com.example.network.mapper

import com.example.model.Category
import com.example.network.model.CategoryDto

fun CategoryDto.toDomain(): Category {
    return Category(
        id = this.id,
        name = this.name,
        emoji = this.emoji,
        isIncome = this.isIncome
    )
}