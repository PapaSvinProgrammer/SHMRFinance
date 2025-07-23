package com.example.pincodescreen.presentation.widget.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pincodescreen.presentation.OtpViewModel
import com.example.pincodescreen.presentation.actionHandler
import com.example.shmrfinance.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FirstCreateOtpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OtpViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val focusRequesters = remember {
        List(OtpViewModel.DEFAULT_LENGTH) {
            FocusRequester()
        }
    }
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current

    var isRepeatInput by remember { mutableStateOf(false) }
    var localCode: List<Int?> by remember {
        mutableStateOf((1..OtpViewModel.DEFAULT_LENGTH).map { null })
    }

    LaunchedEffect(state.isValid) {
        if (state.isValid == true) {
            navController.popBackStack()
        }
    }

    LaunchedEffect(state.focusedIndex) {
        state.focusedIndex?.let { index ->
            focusRequesters.getOrNull(index)?.requestFocus()
        }
    }

    LaunchedEffect(state.code, keyboardManager) {
        val allNumbersEntered = state.code.none { it == null }

        if (allNumbersEntered) {
            focusRequesters.forEach { it.freeFocus() }
            focusManager.clearFocus()
            keyboardManager?.hide()

            if (state.code == localCode) {
                viewModel.setDefaultPinCode()
            }

            if (!isRepeatInput) {
                localCode = state.code
                isRepeatInput = true
                viewModel.resetCode()
            }

            if (state.code != localCode) {
                viewModel.resetCode()
                viewModel.updateValidState(false)
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.code_password))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (!isRepeatInput) {
            MainOtpContent(
                state = state,
                focusRequesters = focusRequesters,
                onAction = { action ->
                    actionHandler(
                        action = action,
                        viewModel = viewModel,
                        focusRequesters = focusRequesters
                    )
                },
                modifier = modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
            )
        }

        AnimatedVisibility(
            visible = isRepeatInput,
            enter = slideInHorizontally(initialOffsetX = { it }),
            exit = slideOutHorizontally(targetOffsetX = { it })
        ) {
            MainOtpContent(
                state = state,
                isRepeat = isRepeatInput,
                isError = !(state.isValid ?: true),
                focusRequesters = focusRequesters,
                onAction = { action ->
                    if (state.isValid == false) {
                        viewModel.updateValidState(null)
                    }

                    actionHandler(
                        action = action,
                        viewModel = viewModel,
                        focusRequesters = focusRequesters
                    )
                },
                modifier = modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
            )
        }
    }
}
