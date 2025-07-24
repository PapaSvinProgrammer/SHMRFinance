package com.example.settings.presentation.widget.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.settings.presentation.widget.listItem.ColorListItem

@Composable
internal fun ColorList(
    modifier: Modifier = Modifier,
    list: List<Color>,
    current: Color,
    onSelect: (Color) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 10.dp)
    ) {
        items(list) { color ->
            ColorListItem(
                color = color,
                isSelected = current == color,
                onClick = { onSelect(color) }
            )
        }
    }
}