package com.example.shmrfinance.ui.widget.listItems

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shmrfinance.ui.widget.components.EmojiCard

@Composable
fun CategoryListItem(
    title: String,
    emoji: String
) {
    ListItem(
        modifier = Modifier.height(70.dp),
        headlineContent = {
            Text(text = title)
        },
        leadingContent = {
            EmojiCard(emoji = emoji)
        },
        colors = ListItemDefaults.colors(MaterialTheme.colorScheme.background)
    )
}