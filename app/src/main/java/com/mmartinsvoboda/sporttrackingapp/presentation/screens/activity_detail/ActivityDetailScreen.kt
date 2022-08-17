package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CardSportAppWithTitle
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CircularProgressIndicatorWithDarkBackground
import com.mmartinsvoboda.sporttrackingapp.presentation.components.ScaffoldSportApp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.map.Map
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail.components.ActivityDetailScreenButtons
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail.components.ActivityDetailScreenDetailsCard
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail.components.ActivityDetailScreenHeader
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ActivityDetailScreen(
    id: Int, navigator: DestinationsNavigator, model: ActivityDetailViewModel = hiltViewModel()
) {
    val state by model.state.collectAsState()

    LaunchedEffect(true) {
        model.activityEventChannelEvents.collect { event ->
            when (event) {
                ActivityDetailEvent.Deleted -> {
                    navigator.navigateUp()
                }
            }
        }
    }

    ScaffoldSportApp(
        topBarTitle = "Activity", topBarDisplayNavigationIcon = true, navigator = navigator
    ) {
        SwipeRefresh(state = SwipeRefreshState(state.isLoading), onRefresh = {
            model.onEvent(ActivityEvent.LoadActivity(true))
        }) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(SportTrackingAppTheme.paddings.defaultPadding)
            ) {
                item {}

                state.activity?.let { sportActivity ->
                    item {
                        ActivityDetailScreenHeader(sportActivity)
                    }

                    item {
                        ActivityDetailScreenDetailsCard(sportActivity)
                    }

                    item {
                        CardSportAppWithTitle(
                            title = "Location",
                            modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                        ) {
                            Map(
                                address = sportActivity.place,
                                title = sportActivity.place,
                                modifier = Modifier
                                    .aspectRatio(1.6f)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Map could not be loaded.",
                                    modifier = Modifier.padding(SportTrackingAppTheme.paddings.defaultPadding)
                                )
                            }
                        }
                    }

                    item {
                        ActivityDetailScreenButtons(state, model, sportActivity)
                    }
                }

                item {}
            }
        }
    }

    CircularProgressIndicatorWithDarkBackground(state.isActionInProgress)
}