package com.example.synchronizationscreen.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.utils.format.FormatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SynchronizationScreen(
    navController: NavController,
    viewModel: SynchronizationViewModel
) {
    val workInfo by viewModel.workInfo.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getWorkInfo()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Синхронизация")
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
        LazyColumn(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
        ) {
            items(
                items = workInfo,
                key = { it.id }
            ) {
                ListItem(
                    headlineContent = {
                        Text(text = it.tags.first().toNormalName())
                    },
                    trailingContent = {
                        val isoDate = FormatDate.millisToIsoDate(it.nextScheduleTimeMillis)
                        Text(
                            text = FormatDate.getPrettyDayAndTime(isoDate),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background)
                )

                HorizontalDivider()
            }
        }
    }
}

private fun String.toNormalName(): String {
    return when (this) {
        else -> "Неизвестно"
    }
}