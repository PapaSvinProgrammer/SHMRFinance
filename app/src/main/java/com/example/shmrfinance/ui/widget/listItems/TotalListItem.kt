package com.example.shmrfinance.ui.widget.listItems

import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun TotalListItem(
    modifier: Modifier = Modifier,
    colors: ListItemColors = ListItemDefaults.colors(MaterialTheme.colorScheme.primaryContainer),
    title: String,
    value: String
) {
    ListItem(
        modifier = modifier,
        colors = colors,
        headlineContent = {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        trailingContent = {
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
    )
}