package com.example.settings.presentation.widget.listItem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.settings.presentation.widget.content.SelectedButton
import com.example.settings.presentation.widget.model.LanguageItem

@Composable
internal fun LanguageListItem(
    item: LanguageItem,
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
        ) {
            LanguageContent(item)
            SelectedButton(isSelected)
        }
    }
}

@Composable
private fun BoxScope.LanguageContent(item: LanguageItem) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.countryFlag,
            fontSize = 40.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = item.text,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}