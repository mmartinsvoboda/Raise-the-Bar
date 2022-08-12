package com.mmartinsvoboda.sporttrackingapp.presentation.ui.attr

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Elevation values that can be themed.
 */
@Immutable
data class DarkMode(val isDarkMode: Boolean = false)

internal val LocalDarkMode = staticCompositionLocalOf { DarkMode() }