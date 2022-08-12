package com.mmartinsvoboda.sporttrackingapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.NavGraphs
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.theme.AppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme(darkMode = isSystemInDarkTheme()) {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}