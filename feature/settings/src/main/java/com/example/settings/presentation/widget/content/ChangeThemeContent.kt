package com.example.settings.presentation.widget.content

import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shmrfinance.ui.R
import androidx.compose.ui.platform.testTag

@Composable
internal fun ChangeThemeContent(
    isChecked: Boolean,
    onClick: (Boolean) -> Unit
) {
    ListItem(
        modifier = Modifier.height(56.dp),
        colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background),
        headlineContent = {
            Text(text = stringResource(R.string.change_theme_title))
        },
        trailingContent = {
            Switch(
                checked = isChecked,
                onCheckedChange = onClick,
                modifier = Modifier.testTag("theme_switch")
            )
        }
    )

    HorizontalDivider()
}