package com.example.settings.presentation.haptic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.HapticFeedbackConstantsCompat
import androidx.navigation.NavController
import com.example.settings.presentation.widget.listItem.HapticListItem
import com.example.shmrfinance.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HapticSettingsScreen(
    navController: NavController,
    viewModel: HapticViewModel
) {
    val view = LocalView.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.hapticks)) },
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
        LazyRow(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                HapticListItem(
                    index = "1",
                    text = "Как клавиатура",
                    isSelect = true,
                    onClick = {
                        view.performHapticFeedback(HapticFeedbackConstantsCompat.KEYBOARD_PRESS)
                    }
                )
            }

            item {
                HapticListItem(
                    index = "2",
                    text = "Длительное",
                    onClick = {
                        view.performHapticFeedback(HapticFeedbackConstantsCompat.LONG_PRESS)
                    }
                )

            }

            item {
                HapticListItem(
                    index = "3",
                    text = "Тик часов",
                    onClick = {
                        view.performHapticFeedback(HapticFeedbackConstantsCompat.CLOCK_TICK)
                    }
                )
            }
        }
    }
}
