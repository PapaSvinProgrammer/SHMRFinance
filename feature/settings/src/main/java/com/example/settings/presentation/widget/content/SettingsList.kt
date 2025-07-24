package com.example.settings.presentation.widget.content

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import com.example.shmrfinance.ui.R
import com.example.ui.navigation.AboutRoute
import com.example.ui.navigation.ColorSelectorRoute
import com.example.ui.navigation.HapticSettingsRoute
import com.example.ui.navigation.LanguageSelectorRoute
import com.example.ui.navigation.NavRoute
import com.example.ui.navigation.SettingsOtpRoute
import com.example.ui.navigation.SynchronizationRoute

@Composable
internal fun SettingsList(
    onClick: (NavRoute) -> Unit
) {
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
            modifier = Modifier.clickable { onClick(it.second) }
        )

        HorizontalDivider()
    }
}

private val settingsList = listOf(
    R.string.main_color to ColorSelectorRoute,
    R.string.hapticks to HapticSettingsRoute,
    R.string.code_password to SettingsOtpRoute,
    R.string.synchronization to SynchronizationRoute,
    R.string.language to LanguageSelectorRoute,
    R.string.about_app to AboutRoute
)
