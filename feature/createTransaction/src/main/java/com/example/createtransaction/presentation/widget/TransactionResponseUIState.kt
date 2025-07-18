package com.example.createtransaction.presentation.widget

import com.example.model.TransactionResponse

internal sealed interface TransactionResponseUIState {
    data class Success(val data: TransactionResponse): TransactionResponseUIState
    data object Loading: TransactionResponseUIState
    data class Error(val error: Throwable): TransactionResponseUIState
}