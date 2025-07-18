package com.example.createtransaction.utils

import com.example.createtransaction.presentation.widget.TransactionResponseUIState
import com.example.ui.dialog.ResultDialogType

internal fun TransactionResponseUIState.toResultType(): ResultDialogType {
    return when (this) {
        is TransactionResponseUIState.Error -> ResultDialogType.ERROR
        TransactionResponseUIState.Loading -> ResultDialogType.LOADING
        is TransactionResponseUIState.Success -> ResultDialogType.SUCCESS
    }
}