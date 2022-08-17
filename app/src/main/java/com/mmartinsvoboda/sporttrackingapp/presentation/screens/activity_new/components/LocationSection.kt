package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.DisplayErrorTextOnError
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewState
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewViewModel

@Composable
fun LocationSection(state: ActivityNewState, model: ActivityNewViewModel) {
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
}