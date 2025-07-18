package com.example.tranasctionanalysis.presentation.widget

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
internal fun DateListItem(
    text: String,
    date: String,
    onDateClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(text) },
        trailingContent = {
            SuggestionChip(
                onClick = onDateClick,
                label = {
                    Text(
                        text = date,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = SuggestionChipDefaults.suggestionChipColors(MaterialTheme.colorScheme.primary),
                border = null,
                shape = CircleShape
            )
        },
        colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background)
    )
}