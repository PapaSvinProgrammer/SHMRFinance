package com.example.shmrfinance.ui.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.network.core.NetworkError
import com.example.network.core.RootError
import com.example.network.core.toSlug
import com.example.shmrfinance.R
import com.example.shmrfinance.presentation.createBankAccount.BasicLoadScreen
import kotlinx.coroutines.delay

enum class ResultDialogType {
    SUCCESS,
    LOADING,
    ERROR
}

@Composable
fun ResultDialog(
    type: ResultDialogType,
    error: RootError? = null,
    onDismissRequest: () -> Unit
) {
    LaunchedEffect(type) {
        if (type != ResultDialogType.LOADING) {
            delay(1000)
            onDismissRequest()
        }
    }

    Dialog(
        onDismissRequest = {}
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (type) {
                ResultDialogType.SUCCESS -> SuccessContent()
                ResultDialogType.LOADING -> BasicLoadScreen()
                ResultDialogType.ERROR -> ErrorContent(error)
            }
        }
    }
}

@Composable
private fun BoxScope.SuccessContent() {
    Text(
        modifier = Modifier.align(Alignment.Center),
        text = stringResource(R.string.success).uppercase(),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
private fun BoxScope.ErrorContent(error: RootError?) {
    var text = "ОШИБКА"

    (error as? NetworkError)?.let {
        text = it.toSlug()
    }

    Text(
        modifier = Modifier.align(Alignment.Center),
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}