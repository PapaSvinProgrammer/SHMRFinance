package com.example.settings.presentation.widget.listItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.settings.presentation.widget.content.SelectedButton

@Composable
internal fun ColorListItem(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth()
                .background(color = color)
        ) {
            SelectedButton(isSelected)
        }
    }
}