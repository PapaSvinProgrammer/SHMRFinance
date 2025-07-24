package com.example.bankaccountscreen.presentation.widget.listItem

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankaccountscreen.presentation.widget.model.ChartListItem

@Composable
internal fun DropDownListItem(
    item: ChartListItem,
    isSelect: Boolean = false,
    onClick: () -> Unit
) {
    val borderColor = if (isSelect)
        MaterialTheme.colorScheme.onSurfaceVariant
    else
        Color.Transparent

    Row(
        modifier = Modifier
            .width(130.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(20.dp),
                color = borderColor
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(item.icon),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(item.text),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}