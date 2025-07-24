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
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.shmrfinance.ui.R
import com.example.synchronizationscreen.utils.PrettyString
import com.example.synchronizationscreen.utils.toNormalName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SynchronizationScreen(
    navController: NavController,
    viewModel: SynchronizationViewModel
) {
    val workInfo by viewModel.workLogInfo.collectAsStateWithLifecycle(listOf())
    val frequencyClock by viewModel.frequencyClock.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.synchronization))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            viewModel.setFrequencyClock()
                            navController.popBackStack()
                        }
                    ) {
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
                key = { it.workerName }
            ) {
                ListItem(
                    headlineContent = {
                        Text(text = it.workerName.toNormalName())
                    },
                    trailingContent = {
                        Text(
                            text = PrettyString.millisToString(it.startTime),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background)
                )

                HorizontalDivider()
            }

            item {
                Text(
                    text = stringResource(R.string.frequency_sync),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(15.dp)
                )

                Slider(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    value = frequencyClock.toFloat(),
                    onValueChange = {
                        viewModel.updateFrequencyClock(it)
                    },
                    valueRange = 6f..24f,
                    steps = 17
                )

                Text(
                    text = frequencyClock.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
            }
        }
    }
}