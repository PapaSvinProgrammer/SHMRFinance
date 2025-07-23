package com.example.settings.presentation.settingsOtp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.shmrfinance.ui.R
import com.example.ui.navigation.OtpRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsOtpScreen(
    navController: NavController,
    viewModel: SettingsOtpViewModel
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_password_auth))
    val state = animateLottieCompositionAsState(composition = composition)
    val authState by viewModel.authState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.code_password)) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                modifier = Modifier.size(200.dp),
                composition = composition,
                progress = { state.progress },
            )

            Spacer(modifier = Modifier.height(20.dp))

            when (authState) {
                true -> {
                    DisableAuthPassword {
                        navController.navigate(
                            OtpRoute(
                                isCreate = false,
                                isDisable = true
                            )
                        ) { launchSingleTop = true }
                    }
                }

                false -> {
                    EnableAuthPassword {
                        navController.navigate(
                            OtpRoute(
                                isCreate = true,
                                isDisable = false
                            )
                        ) { launchSingleTop = true }
                    }
                }
            }
        }
    }
}

@Composable
private fun EnableAuthPassword(
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(text = stringResource(R.string.enable_pin_code))
        },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background),
        modifier = Modifier.clickable(onClick = onClick)
    )
}

@Composable
private fun DisableAuthPassword(
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(text = stringResource(R.string.disable_pin_code))
        },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background),
        modifier = Modifier.clickable(onClick = onClick)
    )
}
