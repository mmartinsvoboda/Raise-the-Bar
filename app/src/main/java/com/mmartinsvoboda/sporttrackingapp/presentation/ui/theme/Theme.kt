package com.mmartinsvoboda.sporttrackingapp.presentation.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.attr.*

private val LightColorPalette = lightColors(
    primary = Color(0xFF009de1),
    primaryVariant = Color(0xFF1e87f0),
    secondary = Color(0xFF009de1),
    secondaryVariant = Color(0xFF1e87f0),
    background = BackgroundColorLight,
    surface = Color.White,
    error = ErrorColor,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

private val DarkColorPalette = darkColors(
    primary = Color(0xFF009de1),
    primaryVariant = Color(0xFF1e87f0),
    secondary = Color(0xFF009de1),
    secondaryVariant = Color(0xFF1e87f0),
    background = BackgroundColorDark,
    surface = SurfaceColorDark,
    error = ErrorColor,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = WhiteDarkMode,
    onSurface = WhiteDarkMode,
    onError = WhiteDarkMode
)

@Composable
fun AppTheme(
    darkMode: Boolean,
    content: @Composable () -> Unit
) {
    SportTrackingAppTheme(
        lightColorPalette = LightColorPalette,
        darkColorPalette = DarkColorPalette,
        darkMode = darkMode,
        content = content
    )
}