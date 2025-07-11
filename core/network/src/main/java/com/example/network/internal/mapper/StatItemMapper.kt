package com.example.network.internal.mapper

import com.example.model.StatItem
import com.example.network.internal.model.StatItemDto

internal fun StatItemDto.toDomain(): StatItem {
    return StatItem(
        categoryId = this.categoryId,
        categoryName = this.categoryName,
        emoji = this.emoji,
        amount = this.amount.toBigDecimal()
    )
}

internal fun List<StatItemDto>.toDomain(): List<StatItem> {
    return this.map { it.toDomain() }
}