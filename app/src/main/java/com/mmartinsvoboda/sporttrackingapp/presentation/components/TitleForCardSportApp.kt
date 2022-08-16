package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun TitleForCardSportApp(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String = "",
    navigationIcon: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null
        )
    },
    navigationAction: (() -> Unit)? = null,
    displayNavigationAction: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = SportTrackingAppTheme.paddings.smallPadding,
                vertical = SportTrackingAppTheme.paddings.tinyPadding
            ),
        horizontalArrangement = if (navigationAction != null && displayNavigationAction) {
            Arrangement.SpaceBetween
        } else {
            Arrangement.Start
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (subtitle.isNotBlank()) 1 else 2
            )

            if (subtitle.isNotBlank()) {
                Text(
                    text = subtitle,
                    style = SportTrackingAppTheme.typography.caption,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }

        if (navigationAction != null && displayNavigationAction) {
            IconButton(
                onClick = navigationAction,
                modifier = Modifier.requiredSize(24.dp)
            ) {
                navigationIcon()
            }
        }
    }
}

@Composable
fun TitleForCardSportApp(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: AnnotatedString,
    navigationIcon: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null
        )
    },
    navigationAction: (() -> Unit)? = null,
    displayNavigationAction: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = SportTrackingAppTheme.paddings.smallPadding,
                vertical = SportTrackingAppTheme.paddings.tinyPadding
            ),
        horizontalArrangement = if (navigationAction != null && displayNavigationAction) {
            Arrangement.SpaceBetween
        } else {
            Arrangement.Start
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (subtitle.isNotBlank()) 1 else 2
            )

            if (subtitle.isNotBlank()) {
                Text(
                    text = subtitle,
                    style = SportTrackingAppTheme.typography.caption,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }

        if (navigationAction != null && displayNavigationAction) {
            IconButton(
                onClick = navigationAction,
                modifier = Modifier.requiredSize(24.dp)
            ) {
                navigationIcon()
            }
        }
    }
}