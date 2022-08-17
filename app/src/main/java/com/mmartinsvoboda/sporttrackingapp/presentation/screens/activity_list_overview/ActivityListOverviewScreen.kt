package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CardSportAppWithTitle
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CircularProgressIndicatorWithDarkBackground
import com.mmartinsvoboda.sporttrackingapp.presentation.components.ScaffoldSportApp
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.components.ActivitiesNotFoundColumn
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.components.SportActivityListItem
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.components.TopBarActionsActivityListOverviewScreen
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.components.WeeklyChallengeCard
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.destinations.ActivityDetailScreenDestination
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.destinations.ActivityNewScreenDestination
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ActivityListOverviewScreen(
    navigator: DestinationsNavigator,
    model: ActivityListOverviewViewModel = hiltViewModel()
) {
    val state by model.state.collectAsState()

    ScaffoldSportApp(
        topBarTitle = if (state.user != null) "Hello, ${state.user}" else "Hello",
        topBarDisplayNavigationIcon = false,
        navigator = navigator,
        topBarActions = {
            TopBarActionsActivityListOverviewScreen(model, navigator)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigator.navigate(ActivityNewScreenDestination) },
                elevation = FloatingActionButtonDefaults.elevation(0.dp)
            ) {
                Icon(
                    Icons.Outlined.Add,
                    "Add"
                )
            }
        }) {
        SwipeRefresh(state = SwipeRefreshState(state.isLoading),
            onRefresh = {
                model.onEvent(ActivityListEvent.LoadActivityList(true))
            }) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(SportTrackingAppTheme.paddings.defaultPadding)
            ) {
                if (state.weeklyChallengeList.isNotEmpty()) {
                    item {
                        WeeklyChallengeCard(state.weeklyChallengeList)
                    }
                }

                item {
                    CardSportAppWithTitle(
                        title = "Activities",
                        modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                    ) {
                        Column {
                            if (state.activities.isEmpty()) {
                                // no data message
                                ActivitiesNotFoundColumn(state, model, navigator)
                            } else {
                                state.activities.forEachIndexed { index, sportActivity ->
                                    SportActivityListItem(
                                        sportActivity = sportActivity,
                                        modifier = Modifier
                                    ) {
                                        navigator.navigate(
                                            ActivityDetailScreenDestination(
                                                sportActivity.id
                                            )
                                        )
                                    }

                                    if (index != state.activities.lastIndex) Divider()
                                }
                            }
                        }
                    }
                }

                item {}
            }
        }
    }


    CircularProgressIndicatorWithDarkBackground(state.isActionInProgress)
}
