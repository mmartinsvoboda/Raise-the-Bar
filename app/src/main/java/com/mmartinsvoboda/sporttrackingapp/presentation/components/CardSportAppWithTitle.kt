package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun CardSportAppWithTitle(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String = "",
    navigationIcon: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.Default.ArrowForward, contentDescription = null
        )
    },
    navigationAction: (() -> Unit)? = null,
    displayNavigationAction: Boolean = true,
    backgroundColor: Color = SportTrackingAppTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TitleForCardSportApp(
            title = title,
            subtitle = subtitle,
            navigationIcon = navigationIcon,
            navigationAction = navigationAction,
            displayNavigationAction = displayNavigationAction
        )

        SpacerTiny()

        CardContent(backgroundColor, contentColor, border, content)
    }
}

@Composable
fun CardContent(
    backgroundColor: Color = SportTrackingAppTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    CardSportApp(
        backgroundColor = backgroundColor, contentColor = contentColor, border = border
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart
        ) {
            content()
        }
    }
}
