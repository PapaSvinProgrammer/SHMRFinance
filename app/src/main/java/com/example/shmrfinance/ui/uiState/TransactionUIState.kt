package com.example.shmrfinance.ui.uiState

import com.example.network.core.RootError
import com.example.network.model.Transaction

sealed interface TransactionUIState {
    data class Success(val data: List<Transaction>): TransactionUIState
    data object Loading: TransactionUIState
    data class Error(val error: RootError): TransactionUIState
}