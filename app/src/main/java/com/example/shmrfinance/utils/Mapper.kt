package com.example.shmrfinance.utils

import com.example.model.Category
import com.example.model.StatItem

fun StatItem.toCategory(): Category {
    return Category(
        id = this.categoryId,
        name = this.categoryName,
        emoji = this.emoji,
        isIncome = false
    )
}