package com.example.shmrfinance.ui.widget.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EmojiCard(
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    emoji: String
) {
    Text(
        modifier = Modifier
            .size(24.dp)
            .background(
                color = backgroundColor,
                shape = CircleShape
            ),
        text = emoji,
        textAlign = TextAlign.Center
    )
}