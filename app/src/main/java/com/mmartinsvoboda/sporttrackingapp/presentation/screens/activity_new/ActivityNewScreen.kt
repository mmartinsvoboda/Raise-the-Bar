package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Note
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportType
import com.mmartinsvoboda.sporttrackingapp.presentation.components.*
import com.mmartinsvoboda.sporttrackingapp.presentation.components.date_time_picker.DateTimePicker
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.math.roundToInt

@RootNavGraph
@Destination
@Composable
fun ActivityNewScreen(
    navigator: DestinationsNavigator,
    model: ActivityNewViewModel = hiltViewModel()
) {
    val state by model.state.collectAsState()

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
            item {
                Column(
                    modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                ) {
                    DropdownMenuSportApp(
                        items = SportType.values().sortedBy { it._name },
                        itemLabel = { it._name },
                        selectedItem = state.sport,
                        searchBoxLabel = "Select a sport",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        model.onEvent(ActivityNewEvent.SetSportType(it))
                    }

                    DisplayErrorTextOnError(state.sportError)
                }
            }

            if (state.sport != null) {
                item {
                    Column(
                        modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                    ) {
                        OutlinedTextField(
                            value = state.performance,
                            onValueChange = { model.onEvent(ActivityNewEvent.SetPerformance(it)) },
                            modifier = Modifier.fillMaxWidth(),
                            label = { state.sport?.sportUnit?._name?.let { Text(text = it) } },
                            trailingIcon = {
                                state.sport?.sportUnit?.unit?.let {
                                    Text(
                                        text = it,
                                        modifier = Modifier.padding(end = SportTrackingAppTheme.paddings.tinyPadding)
                                    )
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            keyboardOptions = KeyboardOptions(
                                autoCorrect = false,
                                keyboardType = KeyboardType.Number
                            )
                        )

                        DisplayErrorTextOnError(state.performanceError)
                    }
                }

                item {
                    Column(
                        modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                    ) {
                        OutlinedTextField(
                            value = state.location,
                            onValueChange = { model.onEvent(ActivityNewEvent.SetLocation(it)) },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Location") },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Place,
                                    contentDescription = null
                                )
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp)
                        )

                        DisplayErrorTextOnError(error = state.locationError)
                    }
                }

                item {
                    Column(
                        modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                    ) {
                        OutlinedTextField(
                            value = state.description,
                            onValueChange = { model.onEvent(ActivityNewEvent.SetDescription(it)) },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Description") },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Note,
                                    contentDescription = null
                                )
                            },
                            singleLine = false,
                            maxLines = 10,
                            shape = RoundedCornerShape(12.dp)
                        )

                        DisplayErrorTextOnError(error = state.descriptionError)
                    }
                }

                item {
                    Column(
                        modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                    ) {
                        DateTimePicker(
                            currentLocalDateTime = state.startDateTime,
                            label = "Start",
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            model.onEvent(
                                ActivityNewEvent.SetStartDateTime(it)
                            )
                        }

                        DisplayErrorTextOnError(state.startDateTimeError)
                    }
                }

                item {
                    Column(
                        modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                    ) {
                        DateTimePicker(
                            currentLocalDateTime = state.endDateTime,
                            label = "End",
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            model.onEvent(
                                ActivityNewEvent.SetEndDateTime(it)
                            )
                        }

                        DisplayErrorTextOnError(state.endDateTimeError)
                    }
                }

                item {
                    val color = animateColorAsState(
                        targetValue = when (state.enjoyment) {
                            in 0..3 -> Color.Red
                            in 4..7 -> SportTrackingAppTheme.colors.secondary
                            else -> Color.Green
                        }
                    )

                    Column(
                        modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                    ) {
                        Text(text = "Enjoyment")

                        SpacerTiny()

                        SliderWithLabel(
                            value = state.enjoyment.toFloat(),
                            onValueChange = { model.onEvent(ActivityNewEvent.SetEnjoyment(it.roundToInt())) },
                            steps = 1,
                            valueRange = 0f..10f,
                            colors = SliderDefaults.colors(
                                activeTrackColor = color.value,
                                thumbColor = color.value
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            finiteEnd = true
                        )

                        DisplayErrorTextOnError(state.enjoymentError)
                    }
                }
            }

            item {}
        }
    }

    CircularProgressIndicatorWithDarkBackground(state.isActionInProgress)
}