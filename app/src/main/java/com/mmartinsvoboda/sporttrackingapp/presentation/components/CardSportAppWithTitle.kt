package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

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
    displayCard: Boolean = true,
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

        CardContent(
            displayCard, content
        )
    }
}

@Composable
fun CardContent(
    displayCard: Boolean, content: @Composable () -> Unit
) {
    if (displayCard) {
        CardSportApp() {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart
            ) {
                content()
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart
        ) {
            content()
        }
    }
}
