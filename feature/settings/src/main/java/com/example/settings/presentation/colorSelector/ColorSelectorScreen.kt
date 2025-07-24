package com.example.settings.presentation.colorSelector

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.settings.presentation.widget.content.ColorList
import com.example.ui.theme.primaryLightBlue
import com.example.ui.theme.primaryLightGreen
import com.example.ui.theme.primaryLightYellow
import com.example.shmrfinance.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ColorSelectorScreen(
    navController: NavController,
    viewModel: ColorSelectorViewModel
) {
    val currentColor by viewModel.currentColor.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.main_color)) },
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
        ColorList(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            list = colors,
            current = Color(currentColor),
            onSelect = { viewModel.setCurrentColor(it.toArgb()) }
        )
    }
}

private val colors = listOf(
    primaryLightGreen,
    primaryLightBlue,
    primaryLightYellow
)