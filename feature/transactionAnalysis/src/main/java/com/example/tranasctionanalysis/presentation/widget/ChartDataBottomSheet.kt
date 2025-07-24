package com.example.tranasctionanalysis.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.charts.utils.generateColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChartDataBottomSheet(
    data: Map<Float, String>,
    onDismiss: () -> Unit
) {
    val colors = generateColors(data.size)

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        LazyColumn {
            itemsIndexed(items = data.values.toList()) { index, item ->
                ListItem(
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(
                                    color = colors[index % colors.size],
                                    shape = CircleShape
                                )
                        )
                    },
                    headlineContent = { Text(text = item) },
                    colors = ListItemDefaults.colors(Color.Transparent)
                )

                HorizontalDivider()
            }
        }
    }
}