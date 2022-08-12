package com.mmartinsvoboda.sporttrackingapp.presentation.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.destinations.ActivityListOverviewScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    model: LoginScreenViewModel = hiltViewModel()
) {
    Column {
        Text(text = "Login")
        Button(onClick = {
            navigator.popBackStack()
            navigator.navigate(
                ActivityListOverviewScreenDestination()
            )
        }) {
            Text(text = "Overview")
        }
    }
}