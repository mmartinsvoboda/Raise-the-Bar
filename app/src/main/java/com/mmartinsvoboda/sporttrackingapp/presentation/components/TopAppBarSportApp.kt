package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun TopAppBarSportApp(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    displayNavigationIcon: Boolean = true,
    actions: @Composable RowScope.() -> Unit = {}
) {
    if (displayNavigationIcon) {
        TopAppBar(
            title,
            modifier,
            navigationIcon,
            actions,
            backgroundColor = SportTrackingAppTheme.colors.background,
            contentColor = SportTrackingAppTheme.colors.onBackground,
            elevation = 0.dp
        )
    } else {
        TopAppBar(
            title,
            modifier,
            actions = actions,
            backgroundColor = SportTrackingAppTheme.colors.background,
            contentColor = SportTrackingAppTheme.colors.onBackground,
            elevation = 0.dp
        )
    }
}