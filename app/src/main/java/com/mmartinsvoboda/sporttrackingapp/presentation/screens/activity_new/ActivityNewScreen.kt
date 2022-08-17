package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CardSportApp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CircularProgressIndicatorWithDarkBackground
import com.mmartinsvoboda.sporttrackingapp.presentation.components.ScaffoldSportApp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerDefault
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.components.*
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph
@Destination
@Composable
fun ActivityNewScreen(
    navigator: DestinationsNavigator, model: ActivityNewViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by model.state.collectAsState()

    LaunchedEffect(true) {
        model.addActivityEventEvents.collect { event ->
            when (event) {
                is AddActivityEvent.Error -> {
                    Toast.makeText(context, event.error, Toast.LENGTH_LONG).show()
                }
                is AddActivityEvent.Success -> {
                    Toast.makeText(context, "Activity added successfuly.", Toast.LENGTH_LONG).show()
                    navigator.navigateUp()
                }
            }
        }
    }

    ScaffoldSportApp(topBarTitle = "New activity",
        topBarDisplayNavigationIcon = true,
        navigator = navigator,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { model.onEvent(ActivityNewEvent.AddNewEvent) },
                elevation = FloatingActionButtonDefaults.elevation(0.dp)
            ) {
                Text(text = "ADD")
            }
        }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SportTrackingAppTheme.paddings.defaultPadding)
        ) {
            item {}

            item {
                CardSportApp(modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)) {
                    Column(
                        modifier = Modifier.padding(SportTrackingAppTheme.paddings.defaultPadding)
                    ) {
                        SwitchSyncToServerSection(state, model)

                        Divider(modifier = Modifier.padding(vertical = SportTrackingAppTheme.paddings.defaultPadding))

                        DropdownMenuWithSportsSection(state, model)

                        SpacerDefault()

                        if (state.sport != null) {
                            PerformanceSection(state, model)

                            SpacerDefault()

                            LocationSection(state, model)

                            SpacerDefault()

                            DescriptionSection(state, model)

                            SpacerDefault()

                            DateTimeSection(state, model)

                            SpacerDefault()

                            EnjoymentSection(state, model)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(SportTrackingAppTheme.paddings.largePadding * 3))
            }
        }
    }

    CircularProgressIndicatorWithDarkBackground(state.isActionInProgress)
}