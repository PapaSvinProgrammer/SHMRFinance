package com.example.ui.uiState

import com.example.model.Transaction

sealed interface TransactionUIState {
    data class Success(val data: List<Transaction>): TransactionUIState
    data object Loading: TransactionUIState
    data class Error(val error: Throwable): TransactionUIState
}