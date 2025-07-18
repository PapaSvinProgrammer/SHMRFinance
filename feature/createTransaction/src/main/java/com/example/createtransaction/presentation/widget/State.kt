package com.example.createtransaction.presentation.widget

import com.example.model.Category
import com.example.model.Transaction
import java.math.BigDecimal

internal data class UiState(
    val currentCategory: Category? = null,
    val date: String = "",
    val time: String = "",
    val balance: BigDecimal = BigDecimal.ZERO,
    val comment: String? = null,
    val transaction: List<Transaction> = listOf(),
    val categories: List<Category> = listOf()
)

internal data class VisibleState(
    val datePicker: Boolean = false,
    val timePicker: Boolean = false,
    val categorySheet: Boolean = false,
    val resultDialog: Boolean = false
)