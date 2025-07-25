package com.example.room.internal.mapper

import com.example.model.Category
import com.example.room.internal.component.category.CategoryEntity

internal fun CategoryEntity.toDomain(): Category {
    return Category(
        id = categoryId,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )
}

internal fun List<CategoryEntity>.toDomain(): List<Category> {
    return map { it.toDomain() }
}

internal fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = 0,
        categoryId = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )
}

internal fun List<Category>.toEntity(): List<CategoryEntity> {
    return map { it.toEntity() }
}