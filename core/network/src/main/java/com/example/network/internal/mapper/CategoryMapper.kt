package com.example.network.internal.mapper

import com.example.model.Category
import com.example.network.internal.model.CategoryDto

internal fun CategoryDto.toDomain(): Category {
    return Category(
        id = this.id,
        name = this.name,
        emoji = this.emoji,
        isIncome = this.isIncome
    )
}