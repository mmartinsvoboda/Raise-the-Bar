package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.presentation.components.ButtonText
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerLarge
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail.ActivityDetailViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail.ActivityEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail.ActivityState
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun ActivityDetailScreenButtons(
    state: ActivityState,
    model: ActivityDetailViewModel,
    sportActivity: SportActivity
) {
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
            ButtonText(text = if (sportActivity.isBackedUp) "Remove from cloud" else "Save to cloud")
        }

        OutlinedButton(
            onClick = {
                model.onEvent(
                    ActivityEvent.ActivityDelete(
                        sportActivity
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            enabled = !state.isActionInProgress
        ) {
            ButtonText(text = "Delete activity")
        }
    }
}