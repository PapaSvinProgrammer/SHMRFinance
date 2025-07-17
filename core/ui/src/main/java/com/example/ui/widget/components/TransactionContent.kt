package com.example.ui.widget.components

import androidx.compose.runtime.Composable
import com.example.model.Transaction

@Composable
fun TransactionContent(
    list: List<Transaction>,
    block: @Composable () -> Unit
) {
    if (list.isEmpty()) {
        EmptyTransactionContent()
    }
    else {
        block()
    }
}