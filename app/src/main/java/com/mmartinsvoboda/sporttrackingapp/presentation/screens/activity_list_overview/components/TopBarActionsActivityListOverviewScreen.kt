package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.ActivityListEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.ActivityListOverviewViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun TopBarActionsActivityListOverviewScreen(
    model: ActivityListOverviewViewModel,
    navigator: DestinationsNavigator
) {

    val expanded = rememberSaveable { mutableStateOf(false) }

    Box(
        Modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded.value = true }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.clip(RoundedCornerShape(12.dp))
        ) {
            DropdownMenuItem(onClick = {
                expanded.value = false
            }) {
                Text("Settings")
            }

            Divider()

            DropdownMenuItem(onClick = {
                expanded.value = false
                model.onEvent(ActivityListEvent.UserLogOut {
                    navigator.popBackStack()
                    navigator.navigate(LoginScreenDestination)
                })
            }) {
                Text("Log out")
            }
        }
    }
}