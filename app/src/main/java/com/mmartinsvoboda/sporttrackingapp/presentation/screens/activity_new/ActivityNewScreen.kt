package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Synchronize to server")

                            Switch(checked = state.syncToServer, onCheckedChange = {
                                model.onEvent(
                                    ActivityNewEvent.SetSyncToServer(
                                        it
                                    )
                                )
                            })
                        }

                        Divider(modifier = Modifier.padding(vertical = SportTrackingAppTheme.paddings.defaultPadding))

                        DropdownMenuSportApp(
                            items = SportType.values().sortedBy { it._name },
                            itemLabel = { it._name },
                            selectedItem = state.sport,
                            searchBoxLabel = "Select a sport",
                            isError = !state.sportError.isNullOrBlank(),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            model.onEvent(ActivityNewEvent.SetSportType(it))
                        }

                        DisplayErrorTextOnError(state.sportError)

                        SpacerDefault()

                        if (state.sport != null) {
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
                                    autoCorrect = false, keyboardType = KeyboardType.Number
                                ),
                                isError = !state.performanceError.isNullOrBlank()
                            )

                            DisplayErrorTextOnError(state.performanceError)

                            SpacerDefault()

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
                                shape = RoundedCornerShape(12.dp),
                                isError = !state.locationError.isNullOrBlank()
                            )

                            DisplayErrorTextOnError(error = state.locationError)

                            SpacerDefault()

                            OutlinedTextField(
                                value = state.description,
                                onValueChange = { model.onEvent(ActivityNewEvent.SetDescription(it)) },
                                modifier = Modifier.fillMaxWidth(),
                                label = { Text(text = "Description") },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Note, contentDescription = null
                                    )
                                },
                                singleLine = false,
                                maxLines = 10,
                                shape = RoundedCornerShape(12.dp),
                                isError = !state.descriptionError.isNullOrBlank()
                            )

                            DisplayErrorTextOnError(error = state.descriptionError)

                            SpacerDefault()

                            DateTimePicker(
                                currentLocalDateTime = state.startDateTime,
                                label = "Start",
                                modifier = Modifier.fillMaxWidth(),
                                isError = !state.startDateTimeError.isNullOrBlank()
                            ) {
                                model.onEvent(
                                    ActivityNewEvent.SetStartDateTime(it)
                                )
                            }

                            DisplayErrorTextOnError(state.startDateTimeError)

                            SpacerDefault()

                            DateTimePicker(
                                currentLocalDateTime = state.endDateTime,
                                label = "End",
                                modifier = Modifier.fillMaxWidth(),
                                isError = !state.endDateTimeError.isNullOrBlank()
                            ) {
                                model.onEvent(
                                    ActivityNewEvent.SetEndDateTime(it)
                                )
                            }

                            DisplayErrorTextOnError(state.endDateTimeError)

                            SpacerDefault()

                            val color = animateColorAsState(
                                targetValue = when (state.enjoyment) {
                                    in 0..3 -> Color.Red
                                    in 4..7 -> SportTrackingAppTheme.colors.secondary
                                    else -> Color.Green
                                }
                            )

                            Text(text = "Enjoyment")

                            SpacerSmall()

                            SliderWithLabel(
                                value = state.enjoyment.toFloat(),
                                onValueChange = { model.onEvent(ActivityNewEvent.SetEnjoyment(it.roundToInt())) },
                                steps = 1,
                                valueRange = 0f..10f,
                                colors = SliderDefaults.colors(
                                    activeTrackColor = color.value, thumbColor = color.value
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                finiteEnd = true
                            )

                            DisplayErrorTextOnError(state.enjoymentError)
                        }
                    }
                }
            }

            item {
                Column {
                    SpacerLarge()
                    SpacerLarge()
                }
            }
        }
    }

    CircularProgressIndicatorWithDarkBackground(state.isActionInProgress)
}