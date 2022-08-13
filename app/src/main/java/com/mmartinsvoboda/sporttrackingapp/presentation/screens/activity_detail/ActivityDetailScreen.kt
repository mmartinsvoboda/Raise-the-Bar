package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.mmartinsvoboda.sporttrackingapp.presentation.components.*
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ActivityDetailScreen(
    id: Int, navigator: DestinationsNavigator, model: ActivityDetailViewModel = hiltViewModel()
) {
    val state by model.state.collectAsState()

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

                item {
                    state.activity?.let { sportActivity ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                        ) {
                            Text(
                                text = sportActivity.name,
                                style = SportTrackingAppTheme.typography.h4,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )

                            SpacerSmall()

                            Text(
                                text = if (sportActivity.isBackedUp) "Synced" else "Not synced",
                                style = SportTrackingAppTheme.typography.caption,
                                color = if (sportActivity.isBackedUp) SportTrackingAppTheme.colors.primary else SportTrackingAppTheme.colors.error
                            )
                        }

                    }
                }

                item {
                    state.activity?.let { sportActivity ->
                        CardSportApp(
                            modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(SportTrackingAppTheme.paddings.defaultPadding)
                            ) {
                                IconAndTextRow(
                                    text = "${sportActivity.start} - ${sportActivity.end}",
                                    icon = Icons.Outlined.Schedule
                                )

                                SpacerTiny()

                                IconAndTextRow(
                                    text = sportActivity.place, icon = Icons.Outlined.Place
                                )
                            }
                        }
                    }
                }

                item {
                    state.activity?.let { sportActivity ->
                        CardSportAppWithTitle(
                            title = "Location",
                            modifier = Modifier
                                .aspectRatio(1.6f)
                                .fillMaxWidth()
                                .padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                        ) {
                            Map(address = sportActivity.place, title = sportActivity.place)
                        }
                    }
                }

                item {
                    state.activity?.let { sportActivity ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                        ) {
                            SpacerLarge()

                            Button(
                                onClick = {
                                    if (sportActivity.isBackedUp) model.onEvent(
                                        ActivityEvent.ActivitySyncOff(
                                            sportActivity
                                        )
                                    )
                                    else model.onEvent(ActivityEvent.ActivitySyncOn(sportActivity))
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                enabled = !state.isActionInProgress
                            ) {
                                Text(text = if (sportActivity.isBackedUp) "Remove from cloud" else "Save to cloud")
                            }

                            OutlinedButton(
                                onClick = {
                                    model.onEvent(ActivityEvent.ActivityDelete(
                                        sportActivity
                                    ) {
                                        navigator.navigateUp()
                                    })
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                enabled = !state.isActionInProgress
                            ) {
                                Text(text = "Delete activity")
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