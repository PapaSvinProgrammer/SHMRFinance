package com.example.pincodescreen.presentation.widget.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pincodescreen.presentation.widget.state.OtpAction
import com.example.pincodescreen.presentation.widget.state.UiState
import com.example.shmrfinance.ui.R

@Composable
internal fun MainOtpContent(
    state: UiState,
    focusRequesters: List<FocusRequester>,
    onAction: (OtpAction) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    hint: String = stringResource(R.string.input_pin_code)
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = hint,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(15.dp))

        MainPinCodeContent(
            state = state,
            isError = isError,
            focusRequesters = focusRequesters,
            onAction = onAction
        )

        AnimatedVisibility(visible = !(state.isValid ?: true)) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.codes_dont_match),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
