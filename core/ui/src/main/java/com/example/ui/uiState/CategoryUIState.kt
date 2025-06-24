package com.example.ui.uiState

import com.example.model.Category

sealed interface CategoryUIState {
    data class Success(val data: List<Category>): CategoryUIState
    data object Loading: CategoryUIState
    data class Error(val error: Throwable): CategoryUIState
}