package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ActivityDetailScreen(
    id: Int,
    navigator: DestinationsNavigator,
    model: ActivityDetailViewModel = hiltViewModel()
) {

    val list = model.listOfActivities.collectAsState()

    Column {
        Text(text = list.value.toString())
    }
}