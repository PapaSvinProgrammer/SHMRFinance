package com.example.model

import java.math.BigDecimal

data class StatItem(
    val categoryId: Int,
    val categoryName: String,
    val emoji: String,
    val amount: BigDecimal
)

fun StatItem.toCategory(): Category {
    return Category(
        id = this.categoryId,
        name = this.categoryName,
        emoji = this.emoji,
        isIncome = false
    )
}