package com.example.ui.uiState

import com.example.model.BankAccount

sealed interface BankAccountUIState {
    data class Success(val data: List<BankAccount>): BankAccountUIState
    data object Loading: BankAccountUIState
    data class Error(val error: Throwable): BankAccountUIState
}