package com.example.shmrfinance.ui.uiState

import com.example.common.RootError
import com.example.model.Category

sealed interface CategoryUIState {
    data class Success(val data: List<Category>): CategoryUIState
    data object Loading: CategoryUIState
    data class Error(val error: RootError): CategoryUIState
}