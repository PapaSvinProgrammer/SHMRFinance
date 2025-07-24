package com.example.settings.presentation.haptic

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.settings.presentation.widget.listItem.HapticListItem
import com.example.settings.presentation.widget.model.hapticsList
import com.example.shmrfinance.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HapticSettingsScreen(
    navController: NavController,
    viewModel: HapticViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .animateContentSize(alignment = Alignment.TopCenter)
        ) {
            ListItem(
                headlineContent = {
                    Text(text = stringResource(R.string.vibration_press))
                },
                trailingContent = {
                    Switch(
                        checked = uiState.switchChecked,
                        onCheckedChange = {
                            viewModel.updateSwitchChecked(it)

                            if (!it) {
                                viewModel.setHapticNumber(-1)
                            }
                        }
                    )
                }
            )

            if (uiState.switchChecked) {
                MainContent(
                    currentHaptic = uiState.hapticNumber,
                    onClick = {
                        viewModel.setHapticNumber(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    currentHaptic: Int,
    onClick: (Int) -> Unit
) {
    val view = LocalView.current

    LazyRow(
        contentPadding = PaddingValues(15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(hapticsList) { hapticItem ->
            HapticListItem(
                subtitle = hapticItem.subtitle,
                text = hapticItem.text,
                isSelect = hapticItem.numberVibration == currentHaptic,
                onClick = {
                    view.performHapticFeedback(hapticItem.numberVibration)
                    onClick(hapticItem.numberVibration)
                }
            )
        }
    }
}