package com.example.ui.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.common.NetworkThrowable
import com.example.common.toSlug
import com.example.ui.uiState.BankAccountUIState
import com.example.ui.widget.components.BasicLoadingScreen
import kotlinx.coroutines.delay

enum class ResultDialogType {
    SUCCESS,
    LOADING,
    ERROR
}

fun BankAccountUIState.toResultType(): ResultDialogType {
    return when (this) {
        is BankAccountUIState.Error -> ResultDialogType.ERROR
        BankAccountUIState.Loading -> ResultDialogType.LOADING
        is BankAccountUIState.Success -> ResultDialogType.SUCCESS
    }
}

@Composable
fun ResultDialog(
    type: ResultDialogType,
    error: Throwable? = null,
    onDismissRequest: () -> Unit
) {
    LaunchedEffect(type) {
        if (type != ResultDialogType.LOADING) {
            delay(2000)
            onDismissRequest()
        }
    }

    Dialog(
        onDismissRequest = {}
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (type) {
                ResultDialogType.SUCCESS -> SuccessContent()
                ResultDialogType.LOADING -> BasicLoadingScreen()
                ResultDialogType.ERROR -> ErrorContent(error)
            }
        }
    }
}

@Composable
private fun BoxScope.SuccessContent() {
    Text(
        modifier = Modifier.align(Alignment.Center),
        textAlign = TextAlign.Center,
        text = "Успешно".uppercase(),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
private fun BoxScope.ErrorContent(error: Throwable?) {
    var text = "ОШИБКА"

    (error as? NetworkThrowable)?.let {
        text = it.toSlug()
    }

    Text(
        modifier = Modifier.align(Alignment.Center),
        textAlign = TextAlign.Center,
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}