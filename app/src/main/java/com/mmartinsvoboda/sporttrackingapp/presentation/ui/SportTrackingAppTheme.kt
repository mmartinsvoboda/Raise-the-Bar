package com.mmartinsvoboda.sporttrackingapp.presentation.ui

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.attr.*

@Composable
fun SportTrackingAppTheme(
    lightColorPalette: Colors,
    darkColorPalette: Colors,
    darkMode: Boolean,
    content: @Composable () -> Unit
) {

    val colors = if (darkMode) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    CompositionLocalProvider(
        LocalPaddings provides Paddings(),
        LocalElevations provides Elevations(),
        LocalDarkMode provides DarkMode(darkMode)
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object SportTrackingAppTheme {
    /**
     * Proxy to [MaterialTheme]
     */
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    /**
     * Proxy to [MaterialTheme]
     */
    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    /**
     * Proxy to [MaterialTheme]
     */
    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    /**
     * Retrieves the current [Paddings].
     */
    val paddings: Paddings
        @Composable
        get() = LocalPaddings.current

    /**
     * Retrieves the current [Paddings].
     */
    val elevations: Elevations
        @Composable
        get() = LocalElevations.current

    /**
     * Retrieves the current dark mode state
     */
    val darkModeState: DarkMode
        @Composable
        get() = LocalDarkMode.current
}