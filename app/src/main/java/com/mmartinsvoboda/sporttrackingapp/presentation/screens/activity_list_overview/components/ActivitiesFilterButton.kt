package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_list_filter.ActivityListFilter
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.ActivityListEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.ActivityListOverviewViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.ActivityListState
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun ActivitiesFilterButton(state: ActivityListState, model: ActivityListOverviewViewModel) {
    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var expanded by remember { mutableStateOf(false) }

    Box(
        Modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        Text(
            text = state.filter._name.uppercase(),
            style = SportTrackingAppTheme.typography.caption,
            color = SportTrackingAppTheme.colors.primary,
            modifier = Modifier.clickable { expanded = !expanded }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.clip(RoundedCornerShape(12.dp))
        ) {
            ActivityListFilter.values().forEachIndexed { index, filter ->
                DropdownMenuItem(
                    onClick = {
                        model.onEvent(
                            ActivityListEvent.FilterActivities(
                                filter
                            )
                        )
                        expanded = false
                    }
                ) {
                    Text(filter._name.uppercase())
                }

                if (index != ActivityListFilter.values().lastIndex) Divider()
            }
        }
    }
}