package com.example.pincodescreen.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import androidx.navigation.NavController
import com.example.pincodescreen.presentation.widget.content.FirstCreateOtpScreen
import com.example.pincodescreen.presentation.widget.content.MainOtpScreen
import com.example.pincodescreen.presentation.widget.state.OtpAction

@Composable
internal fun OtpScreen(
    navController: NavController,
    viewModel: OtpViewModel,
    isCreate: Boolean
) {
    when (isCreate) {
        true -> {
            FirstCreateOtpScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        false -> {
            MainOtpScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

internal fun actionHandler(
    action: OtpAction,
    viewModel: OtpViewModel,
    focusRequesters: List<FocusRequester>
) {
    when (action) {
        is OtpAction.OnEnterNumber -> {
            if (action.number != null) {
                focusRequesters[action.index].freeFocus()
            }
        }

        else -> Unit
    }

    viewModel.onAction(action)
}
