package com.example.ui.widget.listItems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransactionListItem(
    modifier: Modifier = Modifier,
    colors: ListItemColors = ListItemDefaults.colors(MaterialTheme.colorScheme.background),
    title: String,
    amount: String,
    subtitle: String? = null,
    time: String? = null,
    leadingContent: (@Composable () -> Unit)? = null,
) {
    ListItem(
        modifier = modifier,
        colors = colors,
        leadingContent = leadingContent,
        headlineContent = {
            Column {
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                subtitle?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        trailingContent = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = amount,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal
                    )

                    time?.let {
                        Text(
                            modifier = Modifier.align(Alignment.End),
                            text = it,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(-90f),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}