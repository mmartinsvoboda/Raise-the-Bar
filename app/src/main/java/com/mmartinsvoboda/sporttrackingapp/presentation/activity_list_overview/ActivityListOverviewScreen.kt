package com.mmartinsvoboda.sporttrackingapp.presentation.activity_list_overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.destinations.ActivityDetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
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