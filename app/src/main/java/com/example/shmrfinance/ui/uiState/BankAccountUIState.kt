package com.example.shmrfinance.ui.uiState

import com.example.network.core.RootError
import com.example.network.model.BankAccount

sealed interface BankAccountUIState {
    data class Success(val data: List<BankAccount>): BankAccountUIState
    data object Loading: BankAccountUIState
    data class Error(val rootError: RootError): BankAccountUIState
}