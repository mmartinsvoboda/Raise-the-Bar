package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewState
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewViewModel

@Composable
fun SwitchSyncToServerSection(state: ActivityNewState, model: ActivityNewViewModel) {
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
}