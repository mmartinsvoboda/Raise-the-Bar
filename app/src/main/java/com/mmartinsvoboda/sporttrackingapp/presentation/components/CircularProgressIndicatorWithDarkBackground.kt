package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun CircularProgressIndicatorWithDarkBackground(
    visible: Boolean = true
) {
    if (visible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    lightModeAndDarkModeValues(
                        lightMode = Color(0x2D000000),
                        darkMode = Color(0x59000000)
                    )
                )
                .noRippleClickable { },
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun <T> lightModeAndDarkModeValues(
    lightMode: T,
    darkMode: T
): T {
    return if (SportTrackingAppTheme.darkModeState.isDarkMode) {
        darkMode
    } else {
        lightMode
    }
}