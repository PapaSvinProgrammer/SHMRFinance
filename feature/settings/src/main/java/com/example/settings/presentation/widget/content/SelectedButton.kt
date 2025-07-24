package com.example.settings.presentation.widget.content

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun BoxScope.SelectedButton(isSelected: Boolean) {
    val color = if (isSelected)
        MaterialTheme.colorScheme.onBackground
    else
        Color.Transparent

    Icon(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(10.dp),
        imageVector = Icons.Default.CheckCircle,
        contentDescription = null,
        tint = color
    )
}