package com.mmartinsvoboda.sporttrackingapp.presentation.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmartinsvoboda.sporttrackingapp.R
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CircularProgressIndicatorWithDarkBackground
import com.mmartinsvoboda.sporttrackingapp.presentation.components.ScaffoldSportApp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerDefault
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.destinations.ActivityListOverviewScreenDestination
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.login.components.ApplicationLogo
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.login.components.LogInCard
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.login.components.TopBarActionsLoginScreen
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
    val state by model.state.collectAsState()

    LaunchedEffect(true) {
        model.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> navigator.navigate(
                    ActivityListOverviewScreenDestination
                )
            }
        }
    }

    ScaffoldSportApp(
        topBarTitle = stringResource(id = R.string.app_name),
        topBarDisplayNavigationIcon = false,
        topBarActions = {
            TopBarActionsLoginScreen()
        },
        navigator = navigator
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { SpacerDefault() }

            item {
                ApplicationLogo()
            }

            item {
                LogInCard(state, model)
            }

            item {
                SpacerDefault()
            }
        }
    }

    CircularProgressIndicatorWithDarkBackground(state.isLoginInProgress)
}