package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.destinations.ActivityDetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ActivityListOverviewScreen(
    navigator: DestinationsNavigator,
    model: ActivityListOverviewViewModel = hiltViewModel()
) {
    val list = model.listOfActivities.collectAsState()

    Column {
        list.value?.forEach {
            Text(
                text = it.toString(),
                modifier = Modifier.clickable {
                    navigator.navigate(ActivityDetailScreenDestination(it.id))
                }
            )
        }
    }
}