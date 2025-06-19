package com.example.shmrfinance.ui.uiState

import com.example.common.RootError
import com.example.model.BankAccount

sealed interface BankAccountUIState {
    data class Success(val data: List<BankAccount>): BankAccountUIState
    data object Loading: BankAccountUIState
    data class Error(val error: RootError): BankAccountUIState
}