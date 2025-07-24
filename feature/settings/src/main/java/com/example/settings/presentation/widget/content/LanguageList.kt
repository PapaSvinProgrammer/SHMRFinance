package com.example.settings.presentation.widget.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.settings.presentation.widget.listItem.LanguageListItem
import com.example.settings.presentation.widget.model.LanguageItem

@Composable
internal fun LanguageList(
    modifier: Modifier = Modifier,
    list: List<LanguageItem>,
    currentSlug: String,
    onClick: (LanguageItem) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 10.dp)
    ) {
        items(list) { item ->
            LanguageListItem(
                item = item,
                isSelected = item.slug == currentSlug,
                onClick = { onClick(item) }
            )
        }
    }
}