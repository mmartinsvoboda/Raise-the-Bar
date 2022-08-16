package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconAndTextRow(
    text: String,
    icon: ImageVector,
    iconColor: Color? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                icon,
                contentDescription = text,
                modifier = Modifier.requiredSize(18.dp),
                tint = iconColor
                    ?: LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
            )

            SpacerDefault()

            Text(
                text = text
            )
        }
    }
}