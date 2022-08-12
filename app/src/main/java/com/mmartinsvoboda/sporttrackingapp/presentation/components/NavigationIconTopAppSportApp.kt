package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun NavigationIconTopAppBarSportApp(navigator: DestinationsNavigator) {
    IconButton(
        onClick = {
            navigator.navigateUp()
        }
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null
        )
    }
}