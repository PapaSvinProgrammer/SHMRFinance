package com.example.network.mapper

import com.example.model.StatItem
import com.example.network.model.StatItemDto

fun StatItemDto.toDomain(): StatItem {
    return StatItem(
        categoryId = this.categoryId,
        categoryName = this.categoryName,
        emoji = this.emoji,
        amount = this.amount
    )
}

fun List<StatItemDto>.toDomain(): List<StatItem> {
    return this.map { it.toDomain() }
}