package com.example.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ui.navigation.ExpensesRoute
import com.example.shmrfinance.settings.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.settings))
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .verticalScroll(rememberScrollState())
        ) {
            ChangeThemeContent()
            SettingsList()
        }
    }
}

@Composable
private fun ChangeThemeContent() {
    var isChecked by remember { mutableStateOf(false) }

    ListItem(
        modifier = Modifier.height(56.dp),
        colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background),
        headlineContent = {
            Text(text = stringResource(R.string.change_theme_title))
        },
        trailingContent = {
            Switch(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
        }
    )

    HorizontalDivider()
}

@Composable
private fun SettingsList() {
    settingsList.forEach {
        ListItem(
            headlineContent = { Text(text = stringResource(it.first)) },
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(-90f)
                )
            },
            colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background),
            modifier = Modifier.clickable {  }
        )

        HorizontalDivider()
    }
}

private val settingsList = listOf(
    R.string.main_color to ExpensesRoute,
    R.string.sounds to ExpensesRoute,
    R.string.hapticks to ExpensesRoute,
    R.string.code_password to ExpensesRoute,
    R.string.synchronization to ExpensesRoute,
    R.string.language to ExpensesRoute,
    R.string.about_app to ExpensesRoute
)