package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.ButtonText
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerDefault
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.ActivityListEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.ActivityListOverviewViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.ActivityListState
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.destinations.ActivityNewScreenDestination
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun ActivitiesNotFoundColumn(
    state: ActivityListState,
    model: ActivityListOverviewViewModel,
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.padding(SportTrackingAppTheme.paddings.defaultPadding)
    ) {
        Text(text = "No sport activities have been found :(\nGet out there and work out!")

        SpacerDefault()

        Image(
            painter = painterResource(id = state.noDataImage),
            contentDescription = "Sport activity",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 150.dp)
        )

        SpacerDefault()

        Button(
            onClick = {
                navigator.navigate(ActivityNewScreenDestination)
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ButtonText(text = "Add new activity")
        }

        OutlinedButton(
            onClick = {
                model.onEvent(
                    ActivityListEvent.LoadActivityList(
                        true
                    )
                )
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ButtonText(text = "Try again")
        }
    }
}