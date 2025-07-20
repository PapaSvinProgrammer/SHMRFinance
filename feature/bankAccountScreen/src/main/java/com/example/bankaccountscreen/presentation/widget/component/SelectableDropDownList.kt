package com.example.bankaccountscreen.presentation.widget.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.bankaccountscreen.presentation.widget.listItem.DropDownListItem
import com.example.bankaccountscreen.presentation.widget.model.ChartListItem

@Composable
internal fun BoxScope.SelectableDropDownList(
    current: ChartListItem,
    list: List<ChartListItem>,
    onClick: (ChartListItem) -> Unit
) {
    val refactorList = remember(current) { list.weedOutList(current) }
    var isExpanded by remember { mutableStateOf(false) }
    val takeCount = remember(isExpanded) {
        if (isExpanded) refactorList.count() else 0
    }

    Column(
        modifier = Modifier
            .padding(15.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .align(Alignment.TopEnd)
            .background(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.background
            )
            .animateContentSize()
    ) {
        LazyColumn {
            item {
                DropDownListItem(
                    item = current,
                    isSelect = true,
                    onClick = {
                        isExpanded = !isExpanded
                    }
                )
            }

            items(refactorList.take(takeCount)) { item ->
                DropDownListItem(
                    item = item,
                    onClick = {
                        onClick(item)
                        isExpanded = !isExpanded
                    }
                )
            }
        }
    }
}

private fun List<ChartListItem>.weedOutList(current: ChartListItem): List<ChartListItem> {
    val res = mutableListOf<ChartListItem>()

    forEach { item ->
        if (item != current) {
            res.add(item)
        }
    }

    return res
}