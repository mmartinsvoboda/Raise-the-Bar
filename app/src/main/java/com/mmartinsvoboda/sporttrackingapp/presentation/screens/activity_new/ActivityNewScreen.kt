package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CircularProgressIndicatorWithDarkBackground
import com.mmartinsvoboda.sporttrackingapp.presentation.components.ScaffoldSportApp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerDefault
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph
@Destination
@Composable
fun ActivityNewScreen(
    navigator: DestinationsNavigator, model: ActivityNewViewModel = hiltViewModel()
) {
    val state by model.state.collectAsState()

    ScaffoldSportApp(
        topBarTitle = "New activity", topBarDisplayNavigationIcon = true, navigator = navigator
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item { SpacerDefault() }

            item {}

            item {
                SpacerDefault()
            }
        }
    }

    CircularProgressIndicatorWithDarkBackground(state.isActionInProgress)
}