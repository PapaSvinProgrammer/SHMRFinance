package com.example.model

data class StatItem(
    val categoryId: Int,
    val categoryName: String,
    val emoji: String,
    val amount: Float
)

fun StatItem.toCategory(): Category {
    return Category(
        id = this.categoryId,
        name = this.categoryName,
        emoji = this.emoji,
        isIncome = false
    )
}